package com.fresh.dao;

import java.util.List;

import com.fresh.dao.impl.ShopBaseDaoImpl;
import com.fresh.model.Area;
import com.fresh.model.BottomLinks;
import com.fresh.model.Country;
import com.fresh.model.CustomsNews;
import com.fresh.model.Product;
import com.fresh.model.ServiceType;
import com.fresh.model.UsersShow;
import com.google.inject.ImplementedBy;

@ImplementedBy(ShopBaseDaoImpl.class)
public interface ShopBaseDao {

	/**
	 * 获取所有产品
	 */
	List<Product> getProduct();
	
	/**
	 * 获取首页产品
	 */
	List<Product> getProductIndex();
	
	/**
	 * 获取所有地区
	 */
	List<Area> getArea();
	
	/**
	 * 获取所有国家
	 */
	List<Country> getCountry();
	
	
	/**
	 * 获取首页国家
	 */
	List<Country> getCountryIndex();
	
	/**
	 * 获取所有服务类型
	 */
	List<ServiceType> getServiceType();
	
	/**
	 * 获取用户的详细信息
	 */
	List<UsersShow> getUserShowByID(String userID);
	
	/**
	 * 获取海关新闻
	 */
	List<CustomsNews> getCustomsNews();
	
	/**
	 * 获取所有链接地址
	 */
	List<BottomLinks> getBottomLinks();
}
