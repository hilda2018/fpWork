package com.fresh.service.impl;

import java.util.List;

import com.fresh.dao.ShopBaseDao;
import com.fresh.model.Area;
import com.fresh.model.BottomLinks;
import com.fresh.model.Country;
import com.fresh.model.CustomsNews;
import com.fresh.model.Product;
import com.fresh.model.ServiceType;
import com.fresh.model.UsersShow;
import com.fresh.service.ShopBaseService;
import com.google.inject.Inject;

public class ShopBaseServiceImpl implements ShopBaseService {
	
	@Inject
	private ShopBaseDao shopBaseDao;
	
	/**
	 * 获取所有产品
	 */
	public List<Product> getProduct(){
		List<Product> list = shopBaseDao.getProduct();
		return list;
	}
	
	
	/**
	 * 获取前台产品
	 */
	public List<Product> getProductIndex(){
		List<Product> list = shopBaseDao.getProductIndex();
		return list;
	}
	
	/**
	 * 获取所有地区
	 */
	public List<Area> getArea(){
		List<Area> list = shopBaseDao.getArea();
		return list;
	}
	
	/**
	 * 获取所有国家
	 */
	public List<Country> getCountry(){
		List<Country> list = shopBaseDao.getCountry();
		return list;
	}
	
	
	/**
	 * 获取首页国家
	 */
	public List<Country> getCountryIndex(){
		List<Country> list = shopBaseDao.getCountryIndex();
		return list;
	}
	
	/**
	 * 获取所有服务类型
	 */
	public List<ServiceType> getServiceType(){
		List<ServiceType> list = shopBaseDao.getServiceType();
		return list;
	}
	
	/**
	 * 获取用户的详细信息
	 */
	public List<UsersShow> getUserShowByID(String userID){
		List<UsersShow> list = shopBaseDao.getUserShowByID(userID);
		return list;
	}
	
	
	/**
	 * 获取海关新闻
	 */
	 public List<CustomsNews> getCustomsNews(){
		return shopBaseDao.getCustomsNews();
	}
	 
	 /**
		 * 获取所有链接地址
		 */
	  public List<BottomLinks> getBottomLinks(){
		return shopBaseDao.getBottomLinks();
	}
}
