package com.fresh.dao;

import java.util.List;

import com.fresh.dao.impl.CountDaoImpl;
import com.fresh.model.ResultCount;
import com.google.inject.ImplementedBy;

@ImplementedBy(CountDaoImpl.class)
public interface CountDao {

	/**
	 * 获取资讯总数
	 */
	List<ResultCount> getInformationCount();
	
	/**
	 * 获取采购商总数
	 */
	List<ResultCount> getBuyerCount(String productStr,String areaStr,String companyNameStr);
	
	/**
	 * 获取供应商总数
	 */
	List<ResultCount> getSellerCount(String productStr,String countryStr,Integer startMonth,Integer startTen,Integer endMonth,Integer endTen,String companyNameStr);
	
	/**
	 * 获取服务商总数
	 */
	List<ResultCount> getServiceCount(String serviceTypeStr,String companyNameStr);
}
