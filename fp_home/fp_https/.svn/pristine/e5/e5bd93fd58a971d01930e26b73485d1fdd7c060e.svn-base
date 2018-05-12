package com.fresh.dao;

import java.util.List;

import com.fresh.dao.impl.BuyerDaoImpl;
import com.fresh.model.Users;
import com.google.inject.ImplementedBy;

@ImplementedBy(BuyerDaoImpl.class)
public interface BuyerDao {
	/**
	 * 获取采购商信息
	 */
	List<Users> getBuyerList(Integer pagestart,Integer pagesize,String productStr,String areaStr,String companyNameStr);
	
	/**
	 * 获取首页采购商信息
	 */
	List<Users> getBuyerListIndex();
	
}
