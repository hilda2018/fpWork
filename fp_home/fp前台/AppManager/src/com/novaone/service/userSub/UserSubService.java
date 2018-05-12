package com.novaone.service.userSub;

import com.google.inject.ImplementedBy;
import com.novaone.service.userSub.impl.UserSubServiceImpl;

/**
 * 子帐号相关操作接口
 * @author 于采兴
 *
 */
@ImplementedBy(UserSubServiceImpl.class) 
public interface UserSubService {
	
	/**
	 * 删除duser
	 * @param params 接收的参数
	 */
	void delDUser(String params);
	
	/**
	 * 添加或修改用户信息
	 * @param userID 当前主帐号id
	 * @param uid 要修改用户的id
	 * @param username 用户名
	 * @param password 密码
	 * @param email
	 * @param servicetype 服务类型
	 * @param isAdd 是否是添加操作
	 * @param telnum手机号码
	 * @param companycnname 公司名称
	 * @return
	 */
	String registerSubUser(String userID, String uid, String username, String password, 
			String email, String servicetype, boolean isAdd,String telnum,String companycnname);

}
