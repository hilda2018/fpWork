package com.fresh.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.fresh.dao.CountDao;
import com.fresh.model.ResultCount;
import com.nova.frame.utils.JdbcUtils;

public class CountDaoImpl implements CountDao {

	/**
	 * 获取资讯总数
	 */
	@Override
	public List<ResultCount> getInformationCount() {
		List<ResultCount> list = new ArrayList<ResultCount>();
		String sql="SELECT count(*) as countinfo FROM fpdb.information";
		list = JdbcUtils.query(ResultCount.class, sql);
		return list;
	}

	/**
	 * 获取采购商总数
	 */
	@Override
	public List<ResultCount> getBuyerCount(String productStr, String areaStr,String companyNameStr) {
		List<ResultCount> list = new ArrayList<ResultCount>();
		
		StringBuilder sql = new StringBuilder();  
		sql.append("select count(*) as countinfo from fpdb.d_role dr " +
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
		list = JdbcUtils.query(ResultCount.class, sql.toString());
		return list;
	}

	/**
	 * 获取供应商总数
	 */
	@Override
	public List<ResultCount> getSellerCount(String productStr,
			String countryStr, Integer startMonth, Integer startTen,
			Integer endMonth, Integer endTen,String companyNameStr) {
		List<ResultCount> list = new ArrayList<ResultCount>();
		StringBuilder sql = new StringBuilder();  
		sql.append("select count(distinct u.userid) as countinfo from fpdb.d_role dr " +
				"inner join fpdb.d_userrole du on dr.id=du.roleid " +
					"inner join fpdb.users u on u.userid=du.userid ");
		sql.append("left join fpdb.supplierproduct s on s.userid=u.userid " +
				"left join fpdb.product p on p.productid=s.productid ");
		sql.append("left join fpdb.country c2 on u.country=c2.countryid ");
		sql.append("left join fpdb.companytag c3 on u.userid=c3.userid and c3.companytagtype=0 ");
		sql.append("where dr.code=0 ");
		if (productStr!=""){
			sql.append("and p.productenname ='" +productStr+"' ");
		}
		if (countryStr!=""){
			sql.append("and c2.countryenname = '" +countryStr+"'");
		}
		if (startMonth!=0){
			sql.append("and s.startsupplymonth >= " +startMonth+" ");
		}
		if (startTen!=-1){
			sql.append("and s.startsupplyten >= " +startTen+" ");
		}
		if (endMonth!=0){
			sql.append("and s.endsupplymonth <= " +endMonth+" ");
		}
		if (endTen!=-1){
			sql.append("and s.endsupplyten <= " +endTen+" ");
		}
		if (companyNameStr!=""){
			sql.append("and u.companyenname like '%" + companyNameStr + "%' ");
		}
		sql.append("order by u.userid");
		list = JdbcUtils.query(ResultCount.class, sql.toString());
		return list;
	}

	/**
	 * 获取服务商总数
	 */
	@Override
	public List<ResultCount> getServiceCount(String serviceTypeStr,String companyNameStr) {
		List<ResultCount> list = new ArrayList<ResultCount>();
		StringBuilder sql = new StringBuilder();  
		sql.append("select count(*) as countinfo from fpdb.d_role dr " +
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
		list = JdbcUtils.query(ResultCount.class, sql.toString());
		return list;
	}

}
