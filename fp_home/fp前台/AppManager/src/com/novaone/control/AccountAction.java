package com.novaone.control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.google.inject.Inject;
import com.novaone.common.NovaSession;
import com.novaone.common.baseclasses.BaseAction;
import com.novaone.common.exception.NovaException;
import com.novaone.model.Country;
import com.novaone.model.CountryArea;
import com.novaone.model.ServiceType;
import com.novaone.model.User;
import com.novaone.service.db.DBParserAccessService;
import com.novaone.service.user.UserService;
import com.novaone.utils.CommonUtils;
import com.novaone.utils.Md5Encoder;

/**
 * @Description: 账户信息action
 * @author 刘辉
 * @date 2015-7-23 09:47:05
 * @version V1.0
 */
@Action("account")
@Results( {
		@Result(name = "accountShow", location = "accountManagement/accountDetail.jsp"),
		@Result(name = "accountModify", location = "accountManagement/accountModify.jsp"),
		@Result(name = "change_pwd_en", location = "platform/system/changePasswordEn.jsp"),
		@Result(name="resultString",type="json",params={"root","message"})
		})
public class AccountAction extends BaseAction {
	private static final Log logger = LogFactory.getLog(AccountAction.class);
	private static final long serialVersionUID = 1L;
	/**
	 * 登陆用户信息
	 */
	private User user;
	private List<Country> country;
	private List<CountryArea> countryArea;
	private List<ServiceType> serviceType;
	@Inject
	private UserService us;
	@Inject
    private DBParserAccessService dbParserAccessService;
	private Map<String, Object> userInfo;// 用户详细信息
	private String resultString;
	protected HashMap<String, Object> message;
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String accountShow() {

		try {
			NovaSession session = new NovaSession(request.getSession(), true);
			String userID = session.getUserId();
			//String userRoleCode=session.getRoleList().get(0).getCode();
			
			if (userID != null && !"".equals(userID.trim())) {
				user = us.getUserInfo(userID);
				user.setBelongmodule(CommonUtils.getUserRoleCode(userID));
			}

		} catch (Exception e) {
			 logger.error("获取用户信息失败" + e.getMessage());
	         e.printStackTrace();
		}
		return "accountShow";
	}
	
	public String changePwdEn() {

		try {
			NovaSession session = new NovaSession(request.getSession(), true);
			String userID = session.getUserId();

		} catch (Exception e) {
			 logger.error("获取用户信息失败" + e.getMessage());
	         e.printStackTrace();
		}
		return "change_pwd_en";
	}
	public String accountModify() {

		try {
			NovaSession session = new NovaSession(request.getSession(), true);
			String userID = session.getUserId();
			//String userRoleCode=session.getRoleList().get(0).getCode();
			if (userID != null && !"".equals(userID.trim())) {
				user = us.getUserInfo(userID);
				//user.setBelongmodule(CommonUtils.getUserRoleCode(userID));
			}
			country=us.getCountry();
			countryArea=us.getCountryArea();
			serviceType=us.getServiceType();
		} catch (Exception e) {
			 logger.error("获取用户信息失败" + e.getMessage());
	         e.printStackTrace();
		}
		return "accountModify";
	}
	
	public String editUser(){
		message = new HashMap<String, Object>();
		
		//System.out.println("user="+user.getUserid());
		try {
			NovaSession session = new NovaSession(request.getSession(), true);
			String userID = session.getUserId();
			if(us.editUser(user)){
				if("0".equals(CommonUtils.getUserRoleCode(userID))){
					message.put("info", "Success");
	                message.put("status", "YES");
				}else {
					message.put("info", "修改用户信息成功");
	                message.put("status", "YES");
				}
				
			}else {
				if("0".equals(CommonUtils.getUserRoleCode(userID))){
					message.put("info", "Failed");
	                message.put("status", "NO");
				}else {
					message.put("info", "修改用户信息失败");
	                message.put("status", "NO");
				}
				
			}
		} catch (Exception e) {
			logger.error("修改用户信息失败，错误："+ e.getMessage());
		}
		return "resultString";
	}
	/**
	 * 修改密码时验证
	 * @return
	 */
	public String validatePassword(){
		message = new HashMap<String, Object>();
		try {
			NovaSession session = new NovaSession(request.getSession(), true);
			String userID = session.getUserId();
			String oldpassword = String.valueOf(ServletActionContext.getRequest().getParameter("oldpassword"));
			String pwd=us.getPwd(userID);
			String oldPwd=Md5Encoder.encodePassword(oldpassword);
			if(oldPwd.equals(pwd)){
				message.put("info", "");
                message.put("status", "YES");
			}else {
				if("0".equals(CommonUtils.getUserRoleCode(userID))){
					message.put("info", "Original password is incorrect!");
	                message.put("status", "No");
				}else {
					message.put("info", "原密码不正确！");
	                message.put("status", "NO");
				}
			}
		} catch (Exception e) {
			 logger.error("获取用户信息失败" + e.getMessage());
	         e.printStackTrace();
	         message.put("info", "Failed！");
             message.put("status", "No");
		}
		return "resultString";
	}
	/**
	 * 修改密码
	 * @return
	 */
	public String changePassword(){
		message = new HashMap<String, Object>();
		try {
			NovaSession session = new NovaSession(request.getSession(), true);
			String userID = session.getUserId();
			String newpassword = String.valueOf(ServletActionContext.getRequest().getParameter("newpassword"));
			String newPwd=Md5Encoder.encodePassword(newpassword);
			if(us.changePassword(newPwd,userID)){
				if("0".equals(CommonUtils.getUserRoleCode(userID))){
					message.put("info", "Success!");
	                message.put("status", "YES");
				}else {
					message.put("info", "修改成功！");
	                message.put("status", "YES");
				}
			}else {
				if("0".equals(CommonUtils.getUserRoleCode(userID))){
					message.put("info", "Failed!");
	                message.put("status", "No");
				}else {
					message.put("info", "修改失败！");
	                message.put("status", "YES");
				}
			}
		} catch (Exception e) {
			 logger.error("获取用户信息失败" + e.getMessage());
	         e.printStackTrace();
	         message.put("info", "Failed！");
             message.put("status", "No");
		}
		return "resultString";
	}
	/**
     * 获得权限内的功能菜单
     * @return json数据
     */
    public String getMenu(){
        try {
            NovaSession session = new NovaSession(httpSession, true);
            message = us.getMenu(session.getUserId());
        } catch (Exception e) {
            novaException = new NovaException("getMenu", "获取菜单失败", e);
            message = novaException.getJsonData();
        }
        return "resultString";
    }
	public HashMap<String, Object> getMessage() {
		return message;
	}

	public void setMessage(HashMap<String, Object> message) {
		this.message = message;
	}

	public List<Country> getCountry() {
		return country;
	}

	public void setCountry(List<Country> country) {
		this.country = country;
	}

	public List<CountryArea> getCountryArea() {
		return countryArea;
	}

	public void setCountryArea(List<CountryArea> countryArea) {
		this.countryArea = countryArea;
	}

	public List<ServiceType> getServiceType() {
		return serviceType;
	}

	public void setServiceType(List<ServiceType> serviceType) {
		this.serviceType = serviceType;
	}
	
	
	
}
