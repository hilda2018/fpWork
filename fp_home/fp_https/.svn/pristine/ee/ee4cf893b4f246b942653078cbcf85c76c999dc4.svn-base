package com.fresh.service.impl;

import java.util.List;

import com.fresh.dao.CountDao;
import com.fresh.dao.SellerDao;
import com.fresh.dao.ServiceDao;
import com.fresh.model.ResultCount;
import com.fresh.model.Users;
import com.fresh.service.ServiceService;
import com.google.inject.Inject;

public class ServiceServiceImpl implements ServiceService {
	@Inject
	private CountDao countDao;
	
	@Inject
	private ServiceDao serviceDao;
	/**
	 * 获取服务商信息
	 */
	public List<Users> getServiceList(Integer pagestart,Integer pagesize,String serviceTypeStr,String companyNameStr){
		List<Users> list = serviceDao.getServiceList(pagestart, pagesize, serviceTypeStr,companyNameStr);
		return list;
	}
	
	/**
	 * 获取服务商总数
	 */
	public List<ResultCount> getServiceCount(String serviceTypeStr,String companyNameStr){
		List<ResultCount> list=countDao.getServiceCount(serviceTypeStr,companyNameStr);
		return list;
	}
	
	/**
	 * 获取首页服务商信息
	 */
	public List<Users> getServiceListIndex(){
		return serviceDao.getServiceListIndex();
	}
}
