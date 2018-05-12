package com.novaone.control;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.google.inject.Inject;
import com.nova.frame.dao.Base;
import com.nova.frame.dao.buzz.BaseDAO;
import com.nova.frame.dao.criterion.Restrictions;
import com.novaone.common.NovaSession;
import com.novaone.common.baseclasses.BaseAction;
import com.novaone.model.User;
import com.novaone.model.system.Role;
import com.novaone.service.userSub.UserSubService;

/**
 * @Description: 子帐号管理action
 * @author 于采兴
 * @date 2015-11-05 14:47:05
 * @version V1.0
 */
@Action("userSub")
@Results( {
		@Result(name = "addUserSubPage", location = "userSub/register.jsp"),
		@Result(name = "userSubPage", location = "userSub/list.jsp"),
		@Result(name = "userSubMenuPage", location = "userSub/subUserMenu_grid.jsp"),
		@Result(name="resultString",type="json",params={"root","message"})
		})
public class UserSubAction extends BaseAction {
	private static final Log logger = LogFactory.getLog(UserSubAction.class);
	private static final long serialVersionUID = 1L;
	
	@Inject
	private UserSubService userSubservice;
	/**
	 * 登陆用户信息
	 */
	private User user;
	/**主帐号id，用于前端页面**/
	private String masterUserID;
	/**主帐号角色id，用于前端页面**/
	private String masterUserRoleID;
	protected HashMap<String, Object> message;
	/**用于接收参数**/
	protected String params;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	/**
	 * 管理子帐号列表页面
	 * @return
	 */
	public String userSubPage() {
		init();
		return "userSubPage";
	}
	
	private void init(){
		NovaSession session;
		try {
			session = new NovaSession(request.getSession(), true);
			masterUserID = session.getUserId();
			List<Role> roleList = session.getRoleList();
			List<String> roleIds = new ArrayList<String>();
			for(Role r:roleList){
				roleIds.add(r.getId());
			}
		} catch (Exception e) {
			logger.warn(e.getMessage());
		}
	}
	
	/**
	 * 子帐号授权菜单权限操作页面
	 * @return
	 */
	public String userSubMenuPage(){
		init();
		return "userSubMenuPage";
	}
	
	/**
	 * 添加或修改页面
	 * @return
	 */
	public String add() {
		String uid = request.getParameter("uid");
		if(null != uid && !("").equals(uid)){
			User users = new User();
			users.setUserid(uid);
			//user = Base.find(User.class, uid).get();
			user = Base.getByExample(users);
		}
		return "addUserSubPage";
	}

	/**
	 * 删除duser数据
	 */
	public void delDUser(){
		userSubservice.delDUser(params);
	}
	
	/**
	 * 添加或修改子账号
	 */
	public void register(){
		boolean isAdd = false;
		String userid = request.getParameter("userid");
		String username = request.getParameter("username");
		//String password = request.getParameter("password");
		String password ="111111";//默认密码
		String email = request.getParameter("email");
		String servicetype = request.getParameter("servicetype");
		String telnum=request.getParameter("telnum");//电话号码;
		String companycnname=request.getParameter("companycnname");//公司名称
		String userID = "";
		String result="0";
		NovaSession session = null;
		try {
			session = new NovaSession(request.getSession(), true);
			userID = session.getUserId();
		} catch (Exception e) {
			logger.warn(e.getMessage());
		}
		//新建
		if(null == userid || "".equals(userid)){
			isAdd = true;
		}
		if (email == ""){
			//邮箱为空  result="4"   //注册失败
			returnInfo("3");
			return;
		}
		if (email !=""){
			//result = ChkEmailP(email, userid); 
			//邮箱已存在
			if (this.chkEmail(email, userid)){
				returnInfo("2");
				return;
			}
		}
		
		result = userSubservice.registerSubUser(userID, userid, username, password, email, servicetype, isAdd,telnum,companycnname);
		returnInfo(result);
	}
	
	/**
	 * 检查邮箱的私有方法
	 */
//	private String ChkEmailP(String email, String userid){
//		String returnStr = "0";
//		boolean result = this.chkEmail(email, userid);
//
//		//已存在
//		if (result){
//			returnStr="2";
//		}
//		return returnStr;
//	}
	
	/**
	 * 检查邮箱是否已存在
	 * 
	 * @param email
	 * @param userid 要修改用户的id
	 * @return
	 */
	private boolean chkEmail(String email, String userid){
		boolean result = false;

		//新建
		if(null == userid || "".equals(userid)){
			result = Base.exists(User.class, Restrictions.eq("email", email));
		}else{
			//用户修改已存在用户
			result = Base.exists(User.class, Restrictions.neOrIsNotNull("userid", userid), Restrictions.eq("email", email));
		}
		return result;
	}
	
	/**
	 * 检查邮箱
	 */
	public void ChkEmail(){
		String userid = request.getParameter("userid");
		String emailStr = request.getParameter("email");
		String returnStr = "1";
		PrintWriter out = null;
		
		try{
			boolean result = this.chkEmail(emailStr, userid);
			//已存在
			if (result){
				returnStr="0";
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
		String returnStr = "1";
		PrintWriter out = null;
		try{
			User tempUser = new User();
			tempUser.setUsername(usernameStr);
			
			boolean result = Base.existsByExample(tempUser);
			//已存在
			if (result){
				returnStr="0";
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

	public String getMasterUserID() {
		return masterUserID;
	}

	public void setMasterUserID(String masterUserID) {
		this.masterUserID = masterUserID;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}
	
}
