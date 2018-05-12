package com.fresh.dao;

import java.util.List;

import com.fresh.dao.impl.SellerDaoImpl;
import com.fresh.model.SupplierProduct;
import com.fresh.model.Users;
import com.google.inject.ImplementedBy;

@ImplementedBy(SellerDaoImpl.class)
public interface SellerDao {
	/**
	 * 获取供应商信息
	 */
	List<Users> getSellerList(Integer pagestart,Integer pagesize,String productStr,String countryStr,Integer startMonth,Integer startTen,Integer endMonth,Integer endTen,String companyNameStr);
	
	/**
	 * 获取供应商产品图片
	 */
	List<SupplierProduct> getSupplierPrdImg(String userID);
	
	/**
	 * 获取供应商产品信息
	 */
	List<SupplierProduct> getSupplierPrdInfo(String supplierProductID);
	
	/**
	 * 获取首页供应商信息
	 */
	List<Users> getSellerListIndex();
}
