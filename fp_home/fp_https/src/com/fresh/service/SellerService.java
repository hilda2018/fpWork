package com.fresh.service;

import java.util.List;

import com.fresh.model.ResultCount;
import com.fresh.model.SupplierProduct;
import com.fresh.model.Users;
import com.fresh.service.impl.SellerServiceImpl;
import com.google.inject.ImplementedBy;

@ImplementedBy(SellerServiceImpl.class)
public interface SellerService {

	/**
	 * 获取供应商信息
	 */
	List<Users> getSellerList(Integer pagestart,Integer pagesize,String productStr,String countryStr,Integer startMonth,Integer startTen,Integer endMonth,Integer endTen,String companyNameStr);
	
	/**
	 * 获取供应商总数
	 */
	List<ResultCount> getSellerCount(String productStr,String countryStr,Integer startMonth,Integer startTen,Integer endMonth,Integer endTen,String companyNameStr);
	
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
