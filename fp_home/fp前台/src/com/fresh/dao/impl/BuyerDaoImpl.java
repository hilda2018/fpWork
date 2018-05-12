package com.fresh.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.fresh.dao.BuyerDao;
import com.fresh.model.Users;
import com.nova.frame.utils.JdbcUtils;

public class BuyerDaoImpl implements BuyerDao {

	@Override
	public List<Users> getBuyerList(Integer pagestart, Integer pagesize,
			String productStr, String areaStr,String companyNameStr) {
		List<Users> list = new ArrayList<Users>();
		StringBuilder sql = new StringBuilder();  
		sql.append("select u.userid,u.logo,u.companyenname,u.buyerarea,c3.companytaginfo as salesvoiume,u.buyerproduct from fpdb.d_role dr " +
				"inner join fpdb.d_userrole du on dr.id=du.roleid " +
					"inner join fpdb.users u on u.userid=du.userid ");
		if (productStr!=""){
			sql.append("inner join fpdb.companytag c1 on u.userid=c1.userid ");
		}
		if (areaStr!=""){
			sql.append("inner join fpdb.companytag c2 on u.userid=c2.userid ");
		}
		sql.append("left join fpdb.companytag c3 on u.userid=c3.userid and c3.companytagtype=3 ");
		sql.append("where dr.code=1 ");
		if (productStr!=""){
			sql.append("and c1.companytaginfo ='" +productStr+"' and c1.companytagtype=4 ");
		}
		if (areaStr!=""){
			sql.append("and c2.companytaginfo ='" +areaStr+"' and c2.companytagtype=5 ");
		}
		if (companyNameStr!=""){
			sql.append("and u.companyenname like '%" + companyNameStr + "%' ");
		}
		if (pagesize!=0){
			sql.append("limit " + pagestart + "," + pagesize);
		}
		
		list = JdbcUtils.query(Users.class, sql.toString());
		return list;
	}
	
	/**
	 * 获取首页采购商信息
	 */
	public List<Users> getBuyerListIndex(){
		List<Users> list = new ArrayList<Users>();
		String sql="select u.userid,u.logo,u.companyenname,c.companytaginfo as salesvoiume from users u left join fpdb.companytag c on u.userid=c.userid and c.companytagtype=3  where belongmodule=1 and indexshow ='Y' order by indexsortshow limit 0,10";
		list = JdbcUtils.query(Users.class, sql.toString());
		return list;
	}

}
