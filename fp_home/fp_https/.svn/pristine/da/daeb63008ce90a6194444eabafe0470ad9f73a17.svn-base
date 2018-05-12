package com.fresh.dao;

import java.util.List;

import com.fresh.dao.impl.ServiceDaoImpl;
import com.fresh.model.Users;
import com.google.inject.ImplementedBy;

@ImplementedBy(ServiceDaoImpl.class)
public interface ServiceDao {
	/**
	 * 获取服务商信息
	 */
	List<Users> getServiceList(Integer pagestart,Integer pagesize,String serviceTypeStr,String companyNameStr);
	
	/**
	 * 获取首页服务商信息
	 */
	List<Users> getServiceListIndex();
}
