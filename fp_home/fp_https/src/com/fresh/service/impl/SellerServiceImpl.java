package com.fresh.service.impl;

import java.util.List;

import com.fresh.dao.CountDao;
import com.fresh.dao.SellerDao;
import com.fresh.model.ResultCount;
import com.fresh.model.SupplierProduct;
import com.fresh.model.Users;
import com.fresh.service.SellerService;
import com.google.inject.Inject;

public class SellerServiceImpl implements SellerService {
	
	@Inject
	private CountDao countDao;
	
	@Inject
	private SellerDao sellerDao;
	
	/**
	 * 获取供应商信息
	 */
	public List<Users> getSellerList(Integer pagestart,Integer pagesize,String productStr,String countryStr,Integer startMonth,Integer startTen,Integer endMonth,Integer endTen,String companyNameStr){
		List<Users> list = sellerDao.getSellerList(pagestart, pagesize, productStr, countryStr, startMonth, startTen, endMonth, endTen,companyNameStr);
		return list;
	}
	
	/**
	 * 获取供应商总数
	 */
	public List<ResultCount> getSellerCount(String productStr,String countryStr,Integer startMonth,Integer startTen,Integer endMonth,Integer endTen,String companyNameStr){
		List<ResultCount> list=countDao.getSellerCount(productStr, countryStr, startMonth, startTen, endMonth, endTen,companyNameStr);
		return list;
	}
	
	/**
	 * 获取供应商产品图片
	 */
	public List<SupplierProduct> getSupplierPrdImg(String userID){
		List<SupplierProduct> list=sellerDao.getSupplierPrdImg(userID);
		return list;
	}
	
	/**
	 * 获取供应商产品信息
	 */
	public List<SupplierProduct> getSupplierPrdInfo(String supplierProductID){
		List<SupplierProduct> list=sellerDao.getSupplierPrdInfo(supplierProductID);
		return list;
	}
	
	/**
	 * 获取首页供应商信息
	 */
	public List<Users> getSellerListIndex(){
		return sellerDao.getSellerListIndex();
	}
}
