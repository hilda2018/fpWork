package com.fresh.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fresh.dao.AccountDao;
import com.fresh.model.DUsers;
import com.fresh.model.Role;
import com.fresh.model.UserRegis;
import com.fresh.model.Users;
import com.fresh.service.AccountService;
import com.fresh.utils.Md5Encoder;
import com.google.inject.Inject;
import com.nova.frame.utils.JdbcUtils;

public class AccountServiceImpl implements AccountService {

	@Inject
	private AccountDao accountDao;
	
	@Override
	public Boolean chkEmail(String emailStr) {
		return accountDao.chkEmail(emailStr);
	}
	
	/**
	 * 验证用户名
	 */
	public Boolean chkUser(String username){
		return accountDao.chkUser(username);
	}

	@Override
	public String regis(String username, String password,
			String email, String servicetype,String company) {
		String result="0";
		String id= UUID.randomUUID().toString();
		String useridInfo= UUID.randomUUID().toString();
		JdbcUtils.beginTransaction();
		try{
			accountDao.regisUsers(useridInfo, username, Md5Encoder.encodePassword(password), email, servicetype,company);
			accountDao.regisDUsers(useridInfo, username, Md5Encoder.encodePassword(password));
			accountDao.regisUserRole(id, useridInfo, servicetype);
			JdbcUtils.commitTransaction();
		}catch (Exception e) {
			result="4";
			JdbcUtils.rollbackTransaction();
			e.printStackTrace();
		}
		return result;

	}

	@Override
	public List<UserRegis> login(String username, String password) {
		List<UserRegis> list = new ArrayList<UserRegis>();
		list = accountDao.login(username, Md5Encoder.encodePassword(password));
		return list;
	}
	
	@Override
	public List<UserRegis> queryUserList(String username) {
		return accountDao.queryUserList(username);
	}

	@Override
	public Boolean updatePwd(String newPassword,
			String username) {
		return accountDao.updatePwd(Md5Encoder.encodePassword(newPassword),username);
	}
	
	/**
	 * 通过用户名获取Email
	 */
	public String getEmailByName(String username){
		return accountDao.getEmailByName(username);
	}

	/**
	 * 通过用户ID获取用户角色
	 */
	public List<Role> getRoleListByID(String userid){
		return accountDao.getRoleListByID(userid);
	}

	@Override
	public DUsers checkIsusing(String usernameStr) {
		return accountDao.checkIsusing(usernameStr);
	}

	/**
	 * @description 用户申请： 检验邮箱是否存在
	 * @time 2016年7月12日09:49:56
	 * @author wuwenjin
	 */
	@Override
	public Boolean chkEmailForApply(String emailStr) {
		// TODO Auto-generated method stub
		return accountDao.chkEmailForApply(emailStr);
	}

	/**
	 * @description 用户申请： 检验用户名是否存在
	 * @time 2016年7月12日09:49:56
	 * @author wuwenjin
	 */
	@Override
	public Boolean chkUserNameForApply(String usernameStr) {
		// TODO Auto-generated method stub
		return accountDao.chkUserNameForApply(usernameStr);
	}

	@Override
	public String apply(String usernameStr, String passwordStr, String emailStr, String servicetypeStr,
			String company) {
		String result="0";
		String applyId= UUID.randomUUID().toString();
		JdbcUtils.beginTransaction();
		try{
			accountDao.applyUsers(applyId,usernameStr,passwordStr,emailStr,servicetypeStr,company);
			JdbcUtils.commitTransaction();
		}catch (Exception e) {
			result="4";
			JdbcUtils.rollbackTransaction();
			e.printStackTrace();
		}
		return result;

	}
}
