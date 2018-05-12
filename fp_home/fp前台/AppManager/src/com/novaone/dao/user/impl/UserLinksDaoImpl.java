package com.novaone.dao.user.impl;

import java.util.HashMap;

import net.sf.json.JSONObject;

import com.nova.frame.utils.JdbcUtils;
import com.novaone.common.NovaSession;
import com.novaone.dao.db.impl.BaseDataDaoImpl;
import com.novaone.dao.user.UserLinksDao;

public class UserLinksDaoImpl extends BaseDataDaoImpl implements UserLinksDao{

	@Override
	public void afterSave(NovaSession session, JSONObject requestObj, HashMap<String, Object> resultHash){
		
		
		String sql="insert into userlinks(d_user_id,freshport_user_id) values(?,?)";
		String role=session.getRoleList().get(0).getCode().toLowerCase().replace(" ", "");
		int belongModule;
		if("1".equals(role)){
			belongModule=1;//采购商
		}else if ("0".equals(role)) {
			belongModule=0;//供应商
		}else if ("2".equals(role)) {
			belongModule=2;//国内货代
		}else if ("4".equals(role)) {
			belongModule=4;//市场
		}else if ("3".equals(role)) {
			belongModule=3;//第三方验货公司
		}else if ("5".equals(role)) {
			belongModule=5;//海关
		}else if ("6".equals(role)) {
			belongModule=6;//运维
		}else {
			belongModule=100000;//无
		}
		String sqlInsert="insert into users(userID,username) values(?,?)";
		
		JSONObject insertRowsObj = requestObj.getJSONObject("insert");
		int insertRowCount = insertRowsObj.size();
		Object[] insertRowIds = insertRowsObj.keySet().toArray();
		for (int i = 0; i < insertRowCount; i++) {
			String insertRowId = (String) insertRowIds[i];
			JSONObject insertRowObj = insertRowsObj.getJSONObject(insertRowId);
			JdbcUtils.execute(sql, insertRowObj.get("id"),insertRowObj.get("id"));
			JdbcUtils.execute(sqlInsert, insertRowObj.get("id"),insertRowObj.get("id"));
		}
		
		
		
	}
	
}
