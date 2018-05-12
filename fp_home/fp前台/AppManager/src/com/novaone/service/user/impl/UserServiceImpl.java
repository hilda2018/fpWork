package com.novaone.service.user.impl;

import java.util.HashMap;
import java.util.List;

import com.google.inject.Inject;
import com.nova.frame.utils.JdbcUtils;
import com.novaone.dao.user.UserDao;
import com.novaone.model.CompanyTag;
import com.novaone.model.Country;
import com.novaone.model.CountryArea;
import com.novaone.model.DUser;
import com.novaone.model.ServiceType;
import com.novaone.model.User;
import com.novaone.service.user.UserService;

public class UserServiceImpl implements UserService{
	@Inject
    private UserDao userDao;
	@Override
	public User getUserInfo(String userID) {
		return userDao.getUserInfo(userID);
	}
	@Override
	public boolean editUser(User user) {
		 try {
	            JdbcUtils.beginTransaction();// 开启事物
	            int num = userDao.editUser(user);
	            JdbcUtils.commitTransaction();// 提交事物
	            if (num > 0) {
	                return true;
	            }
	        } catch (Exception e) {
	            JdbcUtils.rollbackTransaction();// 回滚事物
	        }
	        return false;
//		if (userDao.editUser(user) > 0) {
//			return true;
//		}else {
//			return false;
//		}
	}
	@Override
	public List<Country> getCountry() {
		return userDao.getCountry();
	}
	@Override
	public List<CountryArea> getCountryArea() {
		return userDao.getCountryArea();
	}
	@Override
	public List<ServiceType> getServiceType() {
		return userDao.getServiceType();
	}
	@Override
	public List<CompanyTag> getCompanyTag(String userID) {
		return userDao.getCompanyTag(userID);
	}
	@Override
	public List<User> queryUserListByType(String type) {
		return userDao.queryUserListByType(type);
	}
	@Override
	public String getPwd(String userID) {
		return userDao.getPwd(userID);
	}
	@Override
	public boolean changePassword(String newPwd, String userID) {
            int num = userDao.changePassword(newPwd,userID);
            if (num > 0) {
                return true;
            }
        return false;
	}
	@Override
	public List<User> getUsersByMoudle(int moudle) {
		List<User> users = userDao.findUserByMoudle(moudle);
		return users;
	}
	
	@Override
	public User findUserByUserid(String userid) {
		
		return userDao.findUserByUserid(userid);
	}
	@Override
	public HashMap<String, Object> getMenu(String userId) throws Exception {
		return userDao.getMenu(userId);
	}
	@Override
	public DUser getDUserbyCode(String code) {
		String sql="select * from d_user where code=? ";
		return JdbcUtils.get(DUser.class, sql, code);
	
	}
	
	
}
