package com.fresh.common.base.action;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.annotations.JSON;
import com.nova.frame.dao.pager.Page;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 本类是系统所有Action类的基类<br>
 * 
 * <b>注意：</b><br>
 * 使用：<br>
 * 有以上功能需求的功能模块的Action继承本类<br>
 * 
 * @author <a href="mailto:yucaixing@novacloud.com">于采兴</a>
 * @version V2.0 2015-5-30 11:26 新建
 */
@SuppressWarnings("serial")
public abstract class BaseAction extends ActionSupport implements Action {

    /** novafram分页 **/
    protected Page page;

    /** 用于接收参数 **/
    protected String params;

    /** 返回DataGrid需要的json对象 */
    protected JSONObject jsonForDG;

    /** 返回页面的信息 */
    protected String returnMsg;

    /** 字段名称唯一性验证返回 **/
    protected HashMap<String, Object> message;

    /** 返回标志 **/
    protected int flag;

    /** request请求对象 */
    protected HttpServletRequest request = ServletActionContext.getRequest();

    /** response响应对象 */
    protected HttpServletResponse response = ServletActionContext.getResponse();

    /** httpSession **/
    protected HttpSession httpSession = (HttpSession) request.getSession();

    /** 国际化(ru_MO=俄文/en_US=英文/zh_CN=中文) **/
    public static String LANG = "ru_mo";

    public BaseAction() {
        // request = ServletActionContext.getRequest();
        // response = ServletActionContext.getResponse();
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public JSONObject getJsonForDG() {
        return jsonForDG;
    }

    public void setJsonForDG(JSONObject jsonForDG) {
        this.jsonForDG = jsonForDG;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    @JSON(serialize = false)
    public HashMap<String, Object> getMessage() {
        return message;
    }

    public void setMessage(HashMap<String, Object> message) {
        this.message = message;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }
    
    /** 判断是否未整数 */
    public boolean chkInteger(String requestStr){
    	Pattern pattern = Pattern.compile("[0-9]*");  
        return pattern.matcher(requestStr).matches();
    }
    
    
    /**
     * 将cookie封装到Map里面
     * @return
     */
    private Map<String,Cookie> ReadCookieMap(){  
        Map<String,Cookie> cookieMap = new HashMap<String,Cookie>();
        Cookie[] cookies = request.getCookies();
        if(null!=cookies){
            for(Cookie cookie : cookies){
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }
    
    
    /**
     * 根据名字获取cookie
     * @param request
     * @param name cookie名字
     * @return
     */
    public Cookie getCookieByName(String name){
        Map<String,Cookie> cookieMap = ReadCookieMap();
        if(cookieMap.containsKey(name)){
            Cookie cookie = (Cookie)cookieMap.get(name);
            return cookie;
        }else{
            return null;
        }   
    }
}
