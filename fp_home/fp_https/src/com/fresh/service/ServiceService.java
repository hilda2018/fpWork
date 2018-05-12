package com.fresh.service;

import java.util.List;

import com.fresh.model.ResultCount;
import com.fresh.model.Users;
import com.fresh.service.impl.ServiceServiceImpl;
import com.google.inject.ImplementedBy;

@ImplementedBy(ServiceServiceImpl.class)
public interface ServiceService {
	
	/**
	 * 获取服务商信息
	 */
	List<Users> getServiceList(Integer pagestart,Integer pagesize,String serviceTypeStr,String companyNameStr);
	
	/**
	 * 获取服务商总数
	 */
	List<ResultCount> getServiceCount(String serviceTypeStr,String companyNameStr);
	
	/**
	 * 获取首页服务商信息
	 */
	List<Users> getServiceListIndex();
}
