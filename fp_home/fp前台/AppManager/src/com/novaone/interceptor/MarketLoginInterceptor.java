package com.novaone.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.StrutsStatics;

import com.google.inject.Inject;
import com.nova.frame.utils.SecurityUtils;
import com.novaone.model.DUser;
import com.novaone.model.User;
import com.novaone.service.app.MarketService;
import com.novaone.utils.Md5Encoder;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
/**
 * 拦截器拦截action中指定方法
 *  [ 项目名 ]  : 
 *  [ 公司名 ]  : 济南易贸创想软件有限公司
 *	[ 模块名 ]  : 
 *	[ 文件名 ]  : 
 *	[ 功 能 ]  : 
 *	[ 作 者 ]  : chenzijun
 *	[ 版 本 ]  : 1.0
 *  [ 时 间 ]  : 2016年1月20日 上午10:09:10
 */
public class MarketLoginInterceptor extends MethodFilterInterceptor {
	 private static final long serialVersionUID =1L;
	 @Inject
		MarketService marketservice;
	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpSession httpSession = (HttpSession) request.getSession();
		//取得请求相关的ActionContext实例 
		ActionContext ctx = invocation.getInvocationContext(); 
		request=(HttpServletRequest) ctx.get(StrutsStatics.HTTP_REQUEST);
		Map<String,Object> session=ctx.getSession();
		System.out.println("=actionName==="+ invocation.getInvocationContext().getName());
		String username=request.getParameter("username");
		String password=request.getParameter("password")+"";
		//password=SecurityUtils.novaEnCryption(password);
		password=Md5Encoder.encodePassword(password);
		
		if(null !=session.get("marketuser")){
			User user = (User) session.get("marketuser");
			if(!username.equalsIgnoreCase(user.getUsername() )|| !password.equalsIgnoreCase(user.getPassword())){
				System.out.println("====="+"登录用户不符,清空session:"+session.get("marketuser"));
				httpSession.removeAttribute("marketuser");
				session.remove("marketuser");
			
			}
		}
		  //取出名为manager的Session属性 
		if(null == session.get("marketuser")){
			//当session为空是,根据变量的值查询设置session
			  User mquser=marketservice.findByCodeAndPwd(username, password);
			  httpSession.setAttribute("marketuser", mquser);
			  if(httpSession.getAttribute("marketuser") !=null){
				  return invocation.invoke();	
			  }
		//	if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) { // 如果是Ajax请求
				resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401
				// session失效
				resp.setContentType("application/json;charset=UTF-8");
				PrintWriter out = null;
				try {
					out = resp.getWriter();
					//登录超时，系统即将跳转到登录页面
					out.println("{\"message\":\"\\u767B\\u5F55\\u8D85\\u65F6\\uFF0C\\u7CFB\\u7EDF\\u5373\\u5C06\\u8DF3\\u8F6C\\u5230\\u767B\\u5F55\\u9875\\u9762\",\"success\":false}");
				} catch (IOException e) {

				} finally {
					if (out != null) {
						out.close();
					}
				}
				return null;
		}else{
			//通知struts验证通过.
			return invocation.invoke();	
		}
	}
}
