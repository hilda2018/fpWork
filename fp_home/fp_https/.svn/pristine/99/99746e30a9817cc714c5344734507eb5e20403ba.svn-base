package com.fresh.dao;

import java.util.List;

import com.fresh.dao.impl.AccountDaoImpl;
import com.google.inject.ImplementedBy;
import com.fresh.model.DUsers;
import com.fresh.model.Role;
import com.fresh.model.UserRegis;
import com.fresh.model.Users;

@ImplementedBy(AccountDaoImpl.class)
public interface AccountDao {
	/**
	 * 验证邮箱
	 */
	Boolean chkEmail(String emailStr);
	
	/**
	 * 验证用户名
	 */
	Boolean chkUser(String username);
	
	/**
	 * 注册用户
	 */
	void regisUsers(String userid,String username, String password, String email,
			String servicetype,String company);
	
	/**
	 * 注册D_User用户
	 */
	void regisDUsers(String userid,String username, String password);
	
	
	/**
	 * 注册用户角色关联
	 */
	void regisUserRole(String id,String userid,String servicetype);
	
	/**
	 * 登录
	 */
	List<UserRegis> login(String username,String password);
	
	/**
	 * 修改密码
	 */
	Boolean updatePwd(String newPassword,String username);
	
	/**
	 * 通过用户名获取Email
	 */
	String getEmailByName(String username);
	
	/**
	 * 通过用户ID获取用户角色
	 */
	List<Role> getRoleListByID(String userid);
	/**
	 * 检查用户是否可用
	 */
	DUsers checkIsusing(String usernameStr);

	List<UserRegis> queryUserList(String username);

	/**
	 * @description 用户申请： 检验邮箱是否存在
	 * @time 2016年7月12日09:49:56
	 * @author wuwenjin
	 */
	Boolean chkEmailForApply(String emailStr);
	/**
	 * @description 用户申请： 检验用户名是否存在
	 * @time 2016年7月12日09:49:56
	 * @author wuwenjin
	 */
	Boolean chkUserNameForApply(String usernameStr);
	/**
	 * @description 用户申请
	 * @time 2016年7月12日09:49:56
	 * @author wuwenjin
	 */
	void applyUsers(String applyIdStr,String usernameStr, String passwordStr, String emailStr, String servicetypeStr, String company);
}
