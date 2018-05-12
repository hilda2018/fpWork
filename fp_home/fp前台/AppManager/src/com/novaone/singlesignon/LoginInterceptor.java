package com.novaone.singlesignon;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.xmlbeans.impl.xb.ltgfmt.Code;
import org.jasig.cas.client.authentication.AttributePrincipal;

import com.novaone.common.CommonInfo;
import com.novaone.common.NovaSession;
import com.novaone.control.JumpController;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 系统的登录验证拦截器<br>
 *     对不需要走拦截器的action请在struts.xml的“<b>excludeActions</b>”属性中配置，多个以英文逗号隔开
 * 
 * @author	<a href="mailto:yucaixing@novacloud.com">于采兴</a>
 * @version V2.0 2015-4-24 10:06 新建
 */
public class LoginInterceptor extends AbstractInterceptor{
	private static final long serialVersionUID = 1L;
	
	/**排除的 action**/
	private String excludeActions;
	/**排除的 action**/
	private static Set<String> NEED_CHECK_URLS = new HashSet<String>();
	private static boolean isInited = false;

	
	/**
	 * 实现AbstractInterceptor里的抽象方法
	 * 
	 * @param	invocation
	 * @return	String
	 */
	public String intercept(ActionInvocation invocation) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String url = request.getRequestURI();
		Object action = invocation.getAction();
		String path =  request.getParameter("p");
		url = URLDecoder.decode(url, "utf-8");
		String coPath = request.getContextPath();
		if(url.indexOf(coPath) != -1){
			url = url.substring(coPath.length()+1, url.length());
		}
		//拦截页面跳转Action
		if(action instanceof JumpController && url.indexOf("p") != -1){
			url += "?p="+path;
		}
		 
		//初始化不需要走过滤器的URL
		initUrl();
		
		//如果当前被拦截类为指定的被排除对象，直接执行下一操作 
		if(needCheck(url)) {
			return  invocation.invoke(); 
		}
		
		HashMap map = getJsessionId(request);
	    if (map.keySet().size() > 0) {
	      request.getSession().setAttribute("userId", map.get("userId"));
	      request.getSession().setAttribute("userName", map.get("userName"));
	      
	    }else{
	    	String method = request.getMethod();
	    	String singletype = request.getParameter("singletype");
	    	//是否需要单点登录验证
	    	if(CommonInfo.SIGNLE_NEED_FLAG.equals(singletype)){
	    		//如果未曾登陆过
		    	//1、获得单点登录地址
		    	String invokeUrl = CommonInfo.SIGNLE_LOGIN_URL;
		    	//获得完成客户端url
		    	String customUrl = this.getCompleteUrl(request);
		    	//获得参数集合
		    	String paramStr = this.getCompleteParam(request);
		    	//单点登录验证
		    	HttpServletResponse response = ServletActionContext.getResponse();
		    	
		    	response.sendRedirect(invokeUrl + customUrl /*+ "?jsonparam="+paramStr*/);
//		    	request.getRequestDispatcher(invokeUrl + customUrl).forward(request, response);
		    	return null;
	    	}
	    	
	    }
	    
	    NovaSession session = new NovaSession(request.getSession());
	    if ((session == null) || (session.getUserId() == null)) {
	      return "login";
	    }
	    return invocation.invoke();
	}
	
	/**
	 * 初始化不需要走过滤器的URL
	 */
	private void initUrl(){
		if (isInited) {
			return;
		}
		String needToCheckUrl = getExcludeActions();
		for (String url : needToCheckUrl.split(",")) {
			LoginInterceptor.NEED_CHECK_URLS.add(url.trim());
		}
		isInited = true;
	}
	
	/*
	 * uri地址是否需要进行过滤
	 * @param uri
	 * @return
	 */
	private boolean needCheck(String uri) {
		boolean needCheck = LoginInterceptor.NEED_CHECK_URLS.contains(uri);
		if (!needCheck) {
			for (String needCheckUrlRegex : NEED_CHECK_URLS) {
				Pattern pattern = Pattern.compile(needCheckUrlRegex);
				Matcher matcher = pattern.matcher(uri);
				needCheck = matcher.matches();

				if (needCheck) {
					break;
				}
			}
		}

		return needCheck;
	}
	
	private HashMap<String, String> getJsessionId(HttpServletRequest request) throws UnsupportedEncodingException {
	    Cookie[] cookies = request.getCookies();
	    String userId = "";
	    String userName = "";
	    HashMap map = new HashMap();
	    if ((cookies != null) && (cookies.length > 0)) {
	      for (Cookie cookie : cookies) {
	        if (cookie.getName().equals("userId")) {
	          userId = cookie.getValue();
//	          map.put("userId", userId);
	        } else if (cookie.getName().equals("userName")) {
	          userName = cookie.getValue();
//	          map.put("userName", URLDecoder.decode(userName,"UTF-8"));
	        }
	      }
	    }
	    
	    AttributePrincipal principal = (AttributePrincipal) request.getUserPrincipal();
	    if(principal == null){
	    	return map;
	    }
	    String userid = principal.getName();
	    if(!userId.equals(userid)){
	    	return map;
	    }
	    String username = (String) principal.getAttributes().get("userName");
//        UserDao dao = new UserDaoImpl();
//        DUser duser = dao.queryByUser(username).get(0);
        map.put("userId", userid);
        map.put("userName", username);
 	    return map;
	}
	/**
	 * 
	 * @方法名称:getCompleteUrl
	 * @内容摘要: 获取完整的URL
	 * @param request
	 * @return 
	 * String
	 * @exception 
	 * @author:张广晨
	 * @创建日期:2016-10-11-下午8:48:02
	 */
	private String getCompleteUrl(HttpServletRequest request){
		String url = CommonInfo.PROJECT_SERVER_URL //服务器地址  
                + ":"   
                + request.getServerPort()           //端口号  
                + request.getContextPath()      //项目名称  
                + request.getServletPath()      //请求页面或其他地址  
//            + "?jsonvalue=" + (request.getQueryString());
               + "?jsonvalue=" + getCompleteParam(request);
		return url;
	}
	/**
	 * 
	 * @方法名称:getCompleteParam
	 * @内容摘要: 获取所有的参数
	 * @param request
	 * @return 
	 * String
	 * @exception 
	 * @author:张广晨
	 * @创建日期:2016-10-11-下午8:49:58
	 */
	private String getCompleteParam(HttpServletRequest request){
		JSONObject item = new JSONObject();
		Enumeration enu=request.getParameterNames();  
		while(enu.hasMoreElements()){  
		String paraName=(String)enu.nextElement();  
		System.out.println(paraName+": "+request.getParameter(paraName));  
		item.put(paraName, request.getParameter(paraName));
		}  
		String str = item.toString();
		str = str.replaceAll("\"", "@");
		
		/*Map map=request.getParameterMap();  
	    Set keSet=map.entrySet();  
	    for(Iterator itr=keSet.iterator();itr.hasNext();){  
	        Map.Entry me=(Map.Entry)itr.next();  
	        Object ok=me.getKey();  
	        Object ov=me.getValue();  
	        String[] value=new String[1];  
	        if(ov instanceof String[]){  
	            value=(String[])ov;  
	        }else{  
	            value[0]=ov.toString();  
	        }  
	  
	        for(int k=0;k<value.length;k++){  
	            System.out.println(ok+"="+value[k]);  
	        }  
	      }  */
		return str;
	}

	public String getExcludeActions() {
		return excludeActions;
	}

	public void setExcludeActions(String excludeActions) {
		this.excludeActions = excludeActions;
	}

	public static Set<String> getNEED_CHECK_URLS() {
		return NEED_CHECK_URLS;
	}

	public static void setNEED_CHECK_URLS(Set<String> nEEDCHECKURLS) {
		NEED_CHECK_URLS = nEEDCHECKURLS;
	}
}
