package com.novaone.service.userSub.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import net.sf.json.JSONObject;

import com.nova.frame.dao.Base;
import com.nova.frame.dao.criterion.Restrictions;
import com.nova.frame.dao.parsing.SQLParser;
import com.nova.frame.utils.JdbcUtils;
import com.novaone.common.util.JSONProcessor;
import com.novaone.model.DUser;
import com.novaone.model.DUserMenu;
import com.novaone.model.User;
import com.novaone.service.userSub.UserSubService;
import com.novaone.utils.Md5Encoder;

public class UserSubServiceImpl implements UserSubService {

	@Override
	public void delDUser(String params) {
		//接收前台传入的json串
        try {
        	JdbcUtils.beginTransaction();
			JSONObject requestObj = JSONProcessor.StrToJSON(params);
			JSONObject rowIdToIdValues = requestObj.getJSONObject("deleteRows");
			List<String> ids = new ArrayList<String>();
			//要删除的id
			for (Object id : rowIdToIdValues.values().toArray()) {
				ids.add((String) id);
			}
			//删除子帐号
			Base.delete(DUser.class, Restrictions.in("id",ids));
			//modify 删除users表中的注册信息.
			Base.delete(User.class, Restrictions.in("userid", ids));
			//删除子帐号的菜单权限
			Base.delete(DUserMenu.class, Restrictions.in("userid",ids));
			JdbcUtils.commitTransaction();
		} catch (Exception e) {
			JdbcUtils.rollbackTransaction();
			e.printStackTrace();
		}
	}
	@Override
	public String registerSubUser(String userID, String uid, String username, String password,
			String email, String servicetype, boolean isAdd,String telnum,String companycnname) {
		//要修改或新增的子帐号id
		String userid= null == uid || "".equals(uid) ? UUID.randomUUID().toString() : uid;
		password = Md5Encoder.encodePassword(password);
		//password = SecurityUtils.novaEnCryption(password);//平台加密方法
		
		try{
			//开事务
			JdbcUtils.beginTransaction();
			//添加或修改子用户
			this.regUser(userid, username, password, email, servicetype, isAdd,telnum,companycnname);
			//添加或修改平台用户
			this.regDUser(userID, userid, username, password,email, isAdd,telnum,companycnname);
			//accountDao.regisUserRole(id, useridInfo, servicetype);
			//新建操作时增加权限
			if(isAdd){
				//新建子帐号时，增加其菜单权限
				this.insertUserMenu(userID, userid);
			}
			JdbcUtils.commitTransaction();//事务提交
		}catch (Exception e) {
			JdbcUtils.rollbackTransaction();//事务回滚
			return "4";
		}
		return "0";
	}
	
	/**
	 * 新建子帐号时，增加其菜单权限
	 * @param userID 主帐号id
	 * @param uid 新建子帐号的id
	 */
	private void insertUserMenu(String userID, String uid){
		List<DUserMenu> newList = new ArrayList<DUserMenu>();
		DUserMenu duMenu = null;
		//查询出当前主帐号所拥有的所有菜单权限
		String sql = SQLParser.sql("userSubDUser.getData_1");
		List<Map<String, Object>> list = JdbcUtils.queryMap(sql, userID);
		
		for(int i = 0; i<list.size(); i++){
			duMenu = new DUserMenu();
			duMenu.setId(UUID.randomUUID().toString());
			duMenu.setMenuid(list.get(i).get("menuid").toString());
			duMenu.setIsenable("Y");
			duMenu.setUserid(uid);
			newList.add(duMenu);
		}
		Base.insert(newList);
	}
	
	/**
	 * 添加子账号用户
	 * @param userid
	 * @param username
	 * @param password
	 * @param email
	 * @param servicetype
	 * @param isAdd
	 * @param telnum
	 * @param companycnname
	 */
	private void regUser(String userid,String username, String password, String email, String servicetype, boolean isAdd,String telnum,String companycnname){
		User users = new User();
		users.setUserid(userid);
		users.setUsername(username);
		users.setPassword(password);
		users.setEmail(email);
		users.setBelongmodule(servicetype);
		
		users.setTelnum(telnum);//增加电话号码
		users.setCompanycnname(companycnname);//增加公司名称
		if(isAdd){
			Base.insert(users);
		}else{
			users.setPassword(null);
			Base.updateNotNull(users);
		}
	}
	
	/**
	 * 添加平台用户
	 * @param userID
	 * @param userid
	 * @param username
	 * @param password
	 * @param isAdd
	 * @param phonenum
	 * @param company
	 * @throws Exception
	 */
	private void regDUser(String userID, String userid,String username, String password,String email, boolean isAdd,String phonenum,String company) throws Exception{
		DUser dUser = new DUser();
		dUser.setId(userid);
		dUser.setCode(username);
		dUser.setName(username);
		dUser.setPassword(password);
		dUser.setIsusing("Y");
		dUser.setParentid(userID);
		if(isAdd){
			Base.insert(dUser);
		}else{
			dUser.setPassword(null);
			Base.updateNotNull(dUser);
		}
	}

}
