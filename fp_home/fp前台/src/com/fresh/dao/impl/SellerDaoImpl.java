package com.fresh.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.fresh.dao.SellerDao;
import com.fresh.model.SupplierProduct;
import com.fresh.model.Users;
import com.nova.frame.utils.JdbcUtils;

public class SellerDaoImpl implements SellerDao {

	@Override
	public List<Users> getSellerList(Integer pagestart, Integer pagesize,
			String productStr, String countryStr, Integer startMonth,
			Integer startTen, Integer endMonth, Integer endTen,String companyNameStr) {
		
		List<Users> list = new ArrayList<Users>();
		StringBuilder sql = new StringBuilder();  
		sql.append("select distinct u.userid,u.logo,u.companyenname,c2.countryenname,c3.companytaginfo as salesvoiume,u.buyerproduct as buyerproduct from fpdb.d_role dr " +
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
		sql.append("order by u.userid ");
		if (pagesize!=0){
			sql.append("limit " + pagestart + "," + pagesize);
		}
		list = JdbcUtils.query(Users.class, sql.toString());
		return list;
	}
	
	/**
	 * 获取供应商产品图片
	 */
	public List<SupplierProduct> getSupplierPrdImg(String userID){
		List<SupplierProduct> list = new ArrayList<SupplierProduct>();
		String sql="select s.supplierproductid,s.supplierproductimg,p.productenname as productid from supplierproduct s "
				+"left join product p on s.productid=p.productid "
				+"where UserID='"+ userID + "'";
		list = JdbcUtils.query(SupplierProduct.class, sql);
		return list;
	}
	
	/**
	 * 获取供应商产品信息
	 */
	public List<SupplierProduct> getSupplierPrdInfo(String supplierProductID){
		List<SupplierProduct> list = new ArrayList<SupplierProduct>();
		StringBuilder sql = new StringBuilder();  
		sql.append("select s.supplierproductid,p.productenname as productid,v.varietiesenname as varietiesid,s.startsupplymonth,s.endsupplymonth,s.startsupplyten,s.endsupplyten, ");
		sql.append("s.moq,mu.measurementunitenname as moqunit,s.yearsupply,mus.measurementunitenname as yearsupplyunit,s.plantarea,mup.measurementunitenname as plantareaunit, ");
		sql.append("s.transport,c.countryenname as countryid,cn.continentenname as countryareaid,s.orchard,s.supplierproductimg,s.introduction, ");
		sql.append("ppv.picvideopath,ppv.picvideonotes,ppv.filetype,ppv.contenttype ");
		sql.append("from supplierproduct s ");
		sql.append("left join product p on s.productid=p.productid ");
		sql.append("left join varieties v on s.varietiesid=v.varietiesid ");
		sql.append("left join measurementunit mu on mu.MeasurementUnitID = s.moqunit ");
		sql.append("left join measurementunit mus on mus.MeasurementUnitID = s.yearsupplyunit ");
		sql.append("left join measurementunit mup on mup.MeasurementUnitID = s.plantareaunit ");
		sql.append("left join country c on c.countryid = s.CountryID ");
		sql.append("left join continent cn on cn.continentid = s.countryareaid ");
		sql.append("left join productpicvideo ppv on s.supplierproductid =ppv.supplierproductid ");
		sql.append("where s.supplierproductid = '" + supplierProductID + "'");
		list = JdbcUtils.query(SupplierProduct.class, sql.toString());
		return list;
	}

	/**
	 * 获取首页供应商信息
	 */
	public List<Users> getSellerListIndex(){
		List<Users> list = new ArrayList<Users>();
		String sql="select u.userid,u.logo,u.companyenname,c.companytaginfo as salesvoiume from users u left join fpdb.companytag c on u.userid=c.userid and c.companytagtype=0  where belongmodule=0 and indexshow ='Y' order by indexsortshow limit 0,10";
		list = JdbcUtils.query(Users.class, sql.toString());
		return list;
	}
}
