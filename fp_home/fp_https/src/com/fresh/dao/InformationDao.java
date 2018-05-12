package com.fresh.dao;

import java.util.List;

import com.fresh.dao.impl.InformationDaoImpl;
import com.fresh.model.Information;
import com.fresh.model.ResultCount;
import com.google.inject.ImplementedBy;

@ImplementedBy(InformationDaoImpl.class)
public interface InformationDao {
	/**
	 * 获取首页资讯信息
	 */
	List<Information> queryIndexNews(Integer pagestart,Integer pagesize);
	
	
	/**
	 * 获取资讯详细信息
	 */
	List<Information> queryDetails(String id);
}
