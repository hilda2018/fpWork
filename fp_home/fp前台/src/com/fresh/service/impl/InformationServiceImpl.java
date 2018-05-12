package com.fresh.service.impl;

import java.util.List;

import com.fresh.dao.CountDao;
import com.fresh.dao.InformationDao;
import com.fresh.model.Information;
import com.fresh.model.ResultCount;
import com.fresh.service.InformationService;
import com.google.inject.Inject;

public class InformationServiceImpl implements InformationService {
	
	@Inject
	private InformationDao informationDao;
	
	@Inject
	private CountDao countDao;
	
	/**
	 * 获取首页资讯信息
	 */
	public List<Information> queryIndexNews(Integer pagestart,Integer pagesize){
		List<Information> list = informationDao.queryIndexNews(pagestart,pagesize);
		return list;
	}
	
	/**
	 * 获取资讯总数
	 */
	public List<ResultCount> queryCount(){
		List<ResultCount> list=countDao.getInformationCount();
		return list;
	}
	
	/**
	 * 获取资讯详细信息
	 */
	public List<Information> queryDetails(String id){
		List<Information> list=informationDao.queryDetails(id);
		return list;
	}
}
