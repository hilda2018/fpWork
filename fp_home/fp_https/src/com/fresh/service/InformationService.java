package com.fresh.service;

import java.util.List;

import com.fresh.model.Information;
import com.fresh.model.ResultCount;
import com.fresh.service.impl.InformationServiceImpl;
import com.google.inject.ImplementedBy;

@ImplementedBy(InformationServiceImpl.class)
public interface InformationService {
	/**
	 * 获取首页资讯信息
	 */
	List<Information> queryIndexNews(Integer pagestart,Integer pagesize);
	
	/**
	 * 获取资讯总数
	 */
	List<ResultCount> queryCount();
	
	/**
	 * 获取资讯详细信息
	 */
	List<Information> queryDetails(String id);
}
