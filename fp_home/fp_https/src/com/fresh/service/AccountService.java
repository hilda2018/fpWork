package com.fresh.service;

import java.util.List;

import com.fresh.model.DUsers;
import com.fresh.model.Role;
import com.fresh.model.UserRegis;
import com.fresh.model.Users;
import com.fresh.service.impl.AccountServiceImpl;
import com.google.inject.ImplementedBy;

@ImplementedBy(AccountServiceImpl.class)
public interface AccountService {
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
	String regis(String username, String password, String email,
			String servicetype,String company);
	
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
	 * 用户申请功能：检查邮箱是否可用
	 * @time 2016年7月12日09:47:10
	 * @author wuwenjin
	 */
	Boolean chkEmailForApply(String emailStr);
	/**
	 * 用户申请功能：检查用户名是否可用
	 * @time 2016年7月12日09:47:10
	 * @author wuwenjin
	 */
	Boolean chkUserNameForApply(String usernameStr);
	/**
	 * 用户申请功能
	 * @time 2016年7月12日09:47:10
	 * @author wuwenjin
	 */
	String apply(String usernameStr, String passwordStr, String emailStr, String servicetypeStr, String company);
}
