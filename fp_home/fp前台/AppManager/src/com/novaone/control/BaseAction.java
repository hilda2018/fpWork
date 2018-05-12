package com.novaone.control;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.annotations.JSON;

import com.nova.frame.dao.pager.Page;
import com.nova.frame.utils.SecurityUtils;
import com.novaone.common.NovaSession;
import com.novaone.common.SystemParameter;
import com.novaone.constants.NovaCloudState;
import com.novaone.model.ResultInfo;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 本类是系统所有Action类的基类<br>
 * 
 * <b>注意：</b><br>
 * 使用：<br>
 * 有以上功能需求的功能模块的Action继承本类<br>
 * 
 */
@SuppressWarnings("serial")
public abstract class BaseAction extends ActionSupport implements Action {

    /** novafram分页 **/
    protected Page novapage=new Page();

    /** 用于接收参数 **/
    protected String params;

    /** 返回DataGrid需要的json对象 */
    protected JSONObject jsonForDG;

    /** 返回页面的信息 */
    protected String returnMsg;

    /** 字段名称唯一性验证返回 **/
    protected HashMap<String, Object> message = new HashMap<String,Object>();
    
    /** 用于前端数据返回 **/
    protected ResultInfo resultInfo = new ResultInfo();

    /** 返回标志 **/
    protected int flag;

    /** request请求对象 */
    protected HttpServletRequest request = ServletActionContext.getRequest();

    /** response响应对象 */
    protected HttpServletResponse response = ServletActionContext.getResponse();

    /** httpSession **/
    protected HttpSession httpSession = (HttpSession) request.getSession();
    
    protected NovaSession session = null;
    /**记录管理员id**/
    protected String userId;
    /**生成默认加密后的密码**/
    protected String password;

    /** 国际化(ru_MO=俄文/en_US=英文/zh_CN=中文) **/
    public static String LANG = "ru_mo";
    
    //获得默认密码
    private String defaultPwd = SystemParameter.getInstance().getValue(NovaCloudState.NOVAONE_DEFAULT_PASSWORD);

    public BaseAction() {
        try {
            session = new NovaSession(request.getSession(), true);
            userId = session.getUserId();
            password = SecurityUtils.novaEnCryption(defaultPwd);
        } catch (Exception e) {
        }
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

    
    public String getUserId() {
        return userId;
    }

    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getPassword() {
        return password;
    }

    
    public void setPassword(String password) {
        this.password = password;
    }

    
    public ResultInfo getResultInfo() {
        return resultInfo;
    }

    
    public void setResultInfo(ResultInfo resultInfo) {
        this.resultInfo = resultInfo;
    }

	public Page getNovapage() {
		return novapage;
	}

	public void setNovapage(Page novapage) {
		this.novapage = novapage;
	}
    
}
