package com.fresh.control;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.util.CommonUtils;

import com.fresh.common.base.action.BaseAction;
import com.fresh.model.DUsers;
import com.fresh.model.Role;
import com.fresh.model.UserRegis;
import com.fresh.service.AccountService;
import com.fresh.utils.MailAccountUtils;
import com.google.inject.Inject;
import com.novaone.common.util.JSONProcessor;

@Action("account")
@ParentPackage("json-default")
@Results({@Result(name = "register", type = "json"),
	@Result(name = "getCode",type = "json"),
	@Result(name = "getCodeByUser",type = "json"),
	@Result(name = "ChkEmail",type = "json"),
	@Result(name = "chkUser",type = "json"),
	@Result(name = "updatePwd",type = "json"),
	@Result(name = "getUser",type = "json"),
	@Result(name = "getRole",type = "json"),
	@Result(name = "clearUser",type = "json"),
	@Result(name = "apply",type = "json"),
	@Result(name = "chkEmailForApply",type = "json"),
	@Result(name = "chkUserNameForApply",type = "json"),
	@Result(name = "login", type = "json")})
public class AccountAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private AccountService accountService;
	

	/**
	 * 登录
	 * @throws UnsupportedEncodingException 
	 */
	public void login() throws UnsupportedEncodingException{
		String captchaId = ""+request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		String usernameStr=java.net.URLDecoder.decode(request.getParameter("username"),"UTF-8");
		String passwordStr=request.getParameter("password");
		String securityCodeStr=request.getParameter("securityCode");
		String result="";
		if (!captchaId.toLowerCase().equals(securityCodeStr.toLowerCase())){
			result="1";    //验证码不对
			returnInfo(result);
			return;
		}
		DUsers du=accountService.checkIsusing(usernameStr);
        if (du != null && "N".equalsIgnoreCase(du.getIsusing())) {
			result="-2";
			returnInfo(result);
			return;
		}
		LoginMethod(request,usernameStr,passwordStr);
	}
	
	/**
	 * 2016-4-17增加单点登录
	 * 单点登录
	 * @throws Exception 
	 */
	public void signleLogin() throws Exception{
		String captchaId = ""+request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
//		String usernameStr=java.net.URLDecoder.decode(request.getParameter("username"),"UTF-8");
//		String passwordStr=request.getParameter("password");
		String result="";
		
		AttributePrincipal principal = (AttributePrincipal) request.getUserPrincipal();
//      Map attributes = principal.getAttributes();
//      AttributePrincipal principal = (AttributePrincipal) request.getUserPrincipal();
      //输入参数
      JSONObject requestObj = JSONProcessor.StrToJSON(params);
//      String usernameStr = principal.getName();
      String usernameStr = (String) principal.getAttributes().get("userName");
		
		DUsers du=accountService.checkIsusing(usernameStr);
		if(du!=null&&"N".equalsIgnoreCase(du.getIsusing())){
			result="-2";
			returnInfo(result);
			return;
		}
		LoginMethod(request,usernameStr,null);
	}
	
	/**
	 * 清空登录信息
	 */
	public void clearUser(){
		try{
			request.getSession().invalidate();
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}
	
	
	
	
	/**
	 * 获取登录信息
	 */
	public void getUser(){
		try{
			HttpSession session = request.getSession();
			if(session!=null) {
				Object attribute = session.getAttribute("userName");
				if(attribute!=null) {
					returnInfo(URLDecoder.decode(attribute.toString(),"UTF-8"));
				}
			}
			returnInfo("");
		}catch (Exception e){
			e.printStackTrace();
			returnInfo("");
		}
		
	}
	
	
	/**
	 * 获取用户角色
	 */
	public void getRole(){
		try{
			Cookie RoleInfo =getCookieByName("Role");
			if (RoleInfo!=null){
				returnInfo(RoleInfo.getValue());
			}else{
				returnInfo("");
			}
		}catch (Exception e){
			e.printStackTrace();
			returnInfo("");
		}
		
	}
	
	
	/**
	 * 获取验证码
	 */
	public void getCode(){
		String captchaId = ""+request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		String emailStr=request.getParameter("email");
		String restu= MailAccountUtils.sendMail(emailStr,0,"Security Code",captchaId);
		PrintWriter out = null;
        try{
        	response.setContentType("text/html; charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			out = response.getWriter();
			out.write(restu);
			out.flush();
        }catch (Exception e){
			e.printStackTrace();
		}finally{
			if(out!=null) {
				out.close();
			}
		}
	}
	
	
	/**
	 * 通过用户名获取验证码
	 */
	public void getCodeByUser(){
		String captchaId = ""+request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		String usernameStr=request.getParameter("username");
		
		String emailStr=accountService.getEmailByName(usernameStr);
		String restu="";
		if (emailStr==""){
			restu= "-1";
		}else{
			restu= MailAccountUtils.sendMail(emailStr,0,"Security Code",captchaId);
		}
		PrintWriter out = null;
        try{
        	response.setContentType("text/html; charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			out = response.getWriter();
			out.write(restu);
			out.flush();
        }catch (Exception e){
			e.printStackTrace();
		}finally{
			if(out!=null) {
				out.close();
			}
		}
	}
	
	/**
	 * 注册
	 * @throws UnsupportedEncodingException 
	 */
	public void register() throws UnsupportedEncodingException{
		String captchaId = ""+request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		String usernameStr=java.net.URLDecoder.decode(request.getParameter("username"),"UTF-8");
		String passwordStr=request.getParameter("password");
		String emailStr=request.getParameter("email");
		String company=request.getParameter("company").replace("%20", " ");
		String securityCodeStr=request.getParameter("securityCode");
		String servicetypeStr=request.getParameter("servicetype");
		String result="";
		if (!captchaId.toLowerCase().equals(securityCodeStr.toLowerCase())){
			/*result="1";    //验证码不对
			returnInfo(result);
			return;*/
		}
		if (emailStr!=""){
			result=ChkEmailP(emailStr);    //邮箱已存在
			if (result.equals("2")){
				returnInfo(result);
				return;
			}
		}
//		if (emailStr==""){
//			result="3";    //邮箱为空  result="4"   //注册失败
//			returnInfo(result);
//			return;
//		}
		result=accountService.regis(usernameStr, passwordStr, emailStr, servicetypeStr,company);
		if (result=="0"){
			returnInfo(result);
			return;
		}else{
			returnInfo(result);
		}
		
	}
	
	private void returnInfo(String result){
		PrintWriter out = null;
		try{
        	response.setContentType("text/html; charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			out = response.getWriter();
			out.write(result);
			out.flush();
        }catch (Exception e){
			e.printStackTrace();
		}finally{
			if(out!=null) {
				out.close();
			}
		}
	}
	
	
	/**
	 * 检查邮箱
	 */
	public void ChkEmail(){
		String emailStr=request.getParameter("email");
		String returnStr="";
		PrintWriter out = null;
		try{
			Boolean result=accountService.chkEmail(emailStr);
			if (result==true){
				returnStr="0";
			}else{
				returnStr="1";
			}
        	response.setContentType("text/html; charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			out = response.getWriter();
			out.write(returnStr);
			out.flush();
        }catch (Exception e){
			e.printStackTrace();
		}finally{
			if(out!=null) {
				out.close();
			}
		}
	}
	
	/**
	 * 检查用户名
	 */
	public void chkUser(){
		String usernameStr=request.getParameter("username");
		String returnStr="";
		PrintWriter out = null;
		try{
			Boolean result=accountService.chkUser(usernameStr);
			if (result==true){
				returnStr="0";
			}else{
				returnStr="1";
			}
        	response.setContentType("text/html; charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			out = response.getWriter();
			out.write(returnStr);
			out.flush();
        }catch (Exception e){
			e.printStackTrace();
		}finally{
			if(out!=null) {
				out.close();
			}
		}
	}
	
	
	/**
	 * 修改密码
	 */
	public void updatePwd(){
		String captchaId = ""+request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		String newPassword=request.getParameter("newPassword");
		String usernameStr=request.getParameter("username");
		String securityCodeStr=request.getParameter("securityCode");
		String returnStr="";
		if (!captchaId.toLowerCase().equals(securityCodeStr.toLowerCase())){
			returnStr="-1";    //验证码不对
			returnInfo(returnStr);
			return;
		}
		Boolean result=accountService.updatePwd(newPassword, usernameStr);
		if (result==true){
			returnStr="0";
		}else{
			returnStr="1";
		}
		returnInfo(returnStr);
	}
	
	/**
	 * 检查邮箱的私有方法
	 */
	private String ChkEmailP(String email){
		String returnStr="";
		Boolean result=accountService.chkEmail(email);
		if (result==true){
			returnStr="0";
		}else{
			returnStr="2";
		}
		return returnStr;
	}

	/**
	 * 登录的私有方法
	 */
	private void LoginMethod(HttpServletRequest request, String usernameStr,String passwordStr){
		String result="";
		List<UserRegis> users = null;
		if(CommonUtils.isEmpty(passwordStr)){
			users = accountService.queryUserList(usernameStr);
		}else{
			users = accountService.login(usernameStr, passwordStr);
		}
		if (users.size()>0){
			result=users.get(0).getuserid();
			try{
				String userName = URLEncoder.encode(users.get(0).getusername(),"UTF-8");
				String userId = result;
				String role = "";
				List<Role> roleList = accountService.getRoleListByID(result);
				if (roleList.size()>0){
					role = roleList.get(0).getRolecode();
				}
				request.getSession().setAttribute("userName", userName);
				request.getSession().setAttribute("userId", userId);
				request.getSession().setAttribute("role", role);
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}else{
			result="-1";
		}
		returnInfo(result);
	}

	
	/**
	 * @description 用户申请功能：检查邮箱
	 * @time 2016年7月12日09:46:02
	 * @param email 
	 * @author wuwenjin
	 */
	public void chkEmailForApply(){
		String emailStr=request.getParameter("email");
		String returnStr="";
		PrintWriter out = null;
		try{
			Boolean result=accountService.chkEmailForApply(emailStr.trim());
			if (result==true){
				returnStr="0";
			}else{
				returnStr="1";
			}
        	response.setContentType("text/html; charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			out = response.getWriter();
			out.write(returnStr);
			out.flush();
        }catch (Exception e){
			e.printStackTrace();
		}finally{
			if(out!=null) {
				out.close();
			}
		}
	}
	
	/**
	 * @description 用户申请功能：检查用户名
	 * @time 2016年7月12日09:46:02
	 * @param username 
	 * @author wuwenjin
	 */
	public void chkUserNameForApply(){
		String usernameStr=request.getParameter("username");
		String returnStr="";
		PrintWriter out = null;
		try{
			Boolean result=accountService.chkUserNameForApply(usernameStr.trim());
			if (result==true){
				returnStr="0";
			}else{
				returnStr="1";
			}
        	response.setContentType("text/html; charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			out = response.getWriter();
			out.write(returnStr);
			out.flush();
        }catch (Exception e){
			e.printStackTrace();
		}finally{
			if(out!=null) {
				out.close();
			}
		}
	}
	
	
	/**
	 * @description 用户申请主方法
	 * @time 2016年7月12日13:23:49
	 * @author wuwenjin
	 * @throws UnsupportedEncodingException 
	 */
	public void apply() throws UnsupportedEncodingException{
		String usernameStr=java.net.URLDecoder.decode(request.getParameter("username"),"UTF-8");
		String passwordStr=request.getParameter("password");
		String emailStr=request.getParameter("email");
		String company=java.net.URLDecoder.decode(request.getParameter("company"),"UTF-8");
		String servicetypeStr=request.getParameter("servicetype");
		String result="";
		result=accountService.apply(usernameStr, passwordStr, emailStr, servicetypeStr,company);
		if (result=="0"){
			returnInfo(result);
			return;
		}else{
			returnInfo(result);
		}
		
	}
}
