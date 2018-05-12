package com.novaone.dao.user;



import java.util.HashMap;
import java.util.List;

import com.google.inject.ImplementedBy;
import com.novaone.dao.user.impl.UserDaoImpl;
import com.novaone.model.CompanyTag;
import com.novaone.model.Country;
import com.novaone.model.CountryArea;
import com.novaone.model.ServiceType;
import com.novaone.model.User;

@ImplementedBy(UserDaoImpl.class)
public interface UserDao {

	/**
     * 
     * 根据用户ID获取用户信息
     * @param userID
     * @return
     * @see [类、类#方法、类#成员]
     */
	public User getUserInfo(String userID);

	public int editUser(User user);

	public List<Country> getCountry();

	public List<CountryArea> getCountryArea();

	public List<ServiceType> getServiceType();

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

	public int changePassword(String newPwd, String userID);

	public List<User> findUserByMoudle(int moudle);

	public User findUserByUserid(String userid);
	/**
     * 获得权限内的功能菜单
     * @return json数据
	 * @throws Exception 
     */
	public HashMap<String, Object> getMenu(String userId) throws Exception;
}
