package com.novaone.service.user;


import java.util.HashMap;
import java.util.List;

import com.google.inject.ImplementedBy;
import com.novaone.model.CompanyTag;
import com.novaone.model.Country;
import com.novaone.model.CountryArea;
import com.novaone.model.DUser;
import com.novaone.model.ServiceType;
import com.novaone.model.User;
import com.novaone.service.user.impl.UserServiceImpl;

@ImplementedBy(UserServiceImpl.class)  
public interface UserService {

	/**
     * 
     * 根据用户ID获取用户信息
     * 
     * @param userID
     * @return
     * @see [类、类#方法、类#成员]
     */
	public User getUserInfo(String userID); 

	/**
	 * 编辑用户信息
	 * @param user
	 * @return
	 */
	public boolean editUser(User user);

	public List<Country> getCountry();

	public List<ServiceType> getServiceType();

	public List<CountryArea> getCountryArea();

	/**
	 * 获取公司标签
	 * @param userID
	 * @return
	 */
	public List<CompanyTag> getCompanyTag(String userID);
	/**
	 * 
	 * @Title: queryUserListByType 
	 * @Description: 根据用户类型查询用户
	 * @param type
	 * @return
	 * @author zhaojiyan
	 * @throws
	 */
	public List<User> queryUserListByType(String type);

	public String getPwd(String userID);

	public boolean changePassword(String newPwd, String userID);
	
	public List<User> getUsersByMoudle(int moudle);

	public User findUserByUserid(String userid);
	/**
     * 获得权限内的功能菜单
     * @return json数据
     */
	public HashMap<String, Object> getMenu(String userId) throws Exception;
	/**
	 * 通过登录名称获取用户信息
	 * @return
	 */
	public DUser getDUserbyCode(String code);

}
