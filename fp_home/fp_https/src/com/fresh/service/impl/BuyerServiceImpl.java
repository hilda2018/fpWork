package com.fresh.service.impl;

import java.util.List;

import com.fresh.dao.BuyerDao;
import com.fresh.dao.CountDao;
import com.fresh.model.Information;
import com.fresh.model.ResultCount;
import com.fresh.model.Users;
import com.fresh.service.BuyerService;
import com.google.inject.Inject;

public class BuyerServiceImpl implements BuyerService {

	
	@Inject
	private CountDao countDao;
	
	@Inject
	private BuyerDao buyerDao;
	
	/**
	 * 获取采购商信息
	 */
	@Override
	public List<Users> getBuyerList(Integer pagestart, Integer pagesize,String productStr,String areaStr,String companyNameStr) {
		List<Users> list = buyerDao.getBuyerList(pagestart, pagesize, productStr, areaStr,companyNameStr);
		return list;
	}

	/**
	 * 获取采购商总数
	 */
	@Override
	public List<ResultCount> queryCount(String productStr,String areaStr,String companyNameStr) {
		List<ResultCount> list=countDao.getBuyerCount(productStr, areaStr,companyNameStr);
		return list;
	}
	
	/**
	 * 获取首页采购商信息
	 */
	public List<Users> getBuyerListIndex(){
		return buyerDao.getBuyerListIndex();
	}

}
