package com.fresh.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.fresh.dao.ServiceDao;
import com.fresh.model.Users;
import com.nova.frame.utils.JdbcUtils;

public class ServiceDaoImpl implements ServiceDao {

	@Override
	public List<Users> getServiceList(Integer pagestart, Integer pagesize,
			String serviceTypeStr,String companyNameStr) {
		List<Users> list = new ArrayList<Users>();
		StringBuilder sql = new StringBuilder();  
		sql.append("select distinct u.userid,u.logo,u.companyenname,c2.countryenname,c1.companytaginfo as salesvoiume,c3.companytaginfo as buyerproduct from fpdb.d_role dr " +
				"inner join fpdb.d_userrole du on dr.id=du.roleid " +
					"inner join fpdb.users u on u.userid=du.userid ");
		sql.append("left join fpdb.companytag c1 on u.userid=c1.userid and c1.companytagtype=1 ");
		sql.append("left join fpdb.country c2 on u.country=c2.countryid ");
		sql.append("left join fpdb.companytag c3 on u.userid=c3.userid and c3.companytagtype=2 ");
		sql.append("where dr.code in(2,3,4) ");
		if (serviceTypeStr!=""){
			sql.append("and c1.companytaginfo ='" +serviceTypeStr+"' and c1.companytagtype=1 ");
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
	 * 获取首页服务商信息
	 */
	public List<Users> getServiceListIndex(){
		List<Users> list = new ArrayList<Users>();
		String sql="select u.userid,u.logo,u.companyenname from users u where belongmodule in(2,3,4) and indexshow ='Y' order by indexsortshow limit 0,10";
		list = JdbcUtils.query(Users.class, sql.toString());
		return list;
	}
}
