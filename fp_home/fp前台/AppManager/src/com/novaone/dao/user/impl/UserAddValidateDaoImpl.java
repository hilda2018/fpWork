package com.novaone.dao.user.impl;

import java.util.Date;

import net.sf.json.JSONObject;

import com.nova.frame.utils.JdbcUtils;
import com.novaone.common.NovaSession;
import com.novaone.common.util.ValueConverter;
import com.novaone.dao.db.impl.BaseDataDaoImpl;
import com.novaone.dao.system.UserDao;
import com.novaone.dao.system.UserDaoImpl;
import com.novaone.dao.user.UserAddValidateDao;
import com.novaone.model.db.ValueType;

public class UserAddValidateDaoImpl extends UserDaoImpl implements UserAddValidateDao {

	/**
	 * 插入之前的操作
	 * 
	 * @param session
	 *            用户 session而非httpsession
	 * @param requestObj
	 *            接收前台传入的json串
	 * @throws Exception
	 * @throws Exception
	 */
	@Override
	protected void beforeSave(NovaSession session, JSONObject requestObj)throws Exception {
		String dateStr = ValueConverter.convertToString(new Date(),
				ValueType.Time);
		// 记录下来修改时间
		JSONObject insertRowsObj = requestObj.getJSONObject("insert");
		int insertRowCount = insertRowsObj.size();
		Object[] insertRowIds = insertRowsObj.keySet().toArray();
		for (int i = 0; i < insertRowCount; i++) {
			String insertRowId = (String) insertRowIds[i];
			JSONObject insertRowObj = insertRowsObj.getJSONObject(insertRowId);
//			insertRowObj.put("currentdate", dateStr);
//			insertRowObj.put("userid", session.getUserId());
			String code= insertRowObj.get("code").toString();
			String name=insertRowObj.get("name").toString();
			int count=JdbcUtils.count("select count(*) from d_user where code=?", code).intValue();
			if(count>0){
				throw new RuntimeException("用户名为"+code+"的用户已存在！");
			}
			count=JdbcUtils.count("select count(*) from d_user where name=?", name).intValue();
			if(count>0){
				throw new RuntimeException("姓名为"+name+"的用户已存在！");
			}
		}
		/*JSONObject updateRowsObj = requestObj.getJSONObject("update");
		int updateRowCount = updateRowsObj.size();
		Object[] updateRowIds = updateRowsObj.keySet().toArray();
		for (int i = 0; i < updateRowCount; i++) {
			String updateRowId = (String) updateRowIds[i];
			JSONObject updateRowObj = updateRowsObj.getJSONObject(updateRowId);
			String code= updateRowObj.get("code").toString();
			String name=updateRowObj.get("name").toString();
			int count=JdbcUtils.count("select count(*) from d_user where code=?", code).intValue();
			if(count>0){
				throw new RuntimeException("用户名为"+code+"的用户已存在！");
			}
			count=JdbcUtils.count("select count(*) from d_user where name=?", name).intValue();
			if(count>0){
				throw new RuntimeException("姓名为"+name+"的用户已存在！");
			}
		}*/
		super.beforeSave(session, requestObj);
	}
	
}
