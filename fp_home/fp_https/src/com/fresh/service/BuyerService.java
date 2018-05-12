package com.fresh.service;

import java.util.List;

import com.fresh.model.ResultCount;
import com.fresh.model.Users;
import com.fresh.service.impl.BuyerServiceImpl;
import com.google.inject.ImplementedBy;

@ImplementedBy(BuyerServiceImpl.class)
public interface BuyerService {
	
	/**
	 * 获取采购商信息
	 */
	List<Users> getBuyerList(Integer pagestart,Integer pagesize,String productStr,String areaStr,String companyNameStr);
	
	/**
	 * 获取采购商总数
	 */
	List<ResultCount> queryCount(String productStr,String areaStr,String companyNameStr);
	
	/**
	 * 获取首页采购商信息
	 */
	List<Users> getBuyerListIndex();
}
