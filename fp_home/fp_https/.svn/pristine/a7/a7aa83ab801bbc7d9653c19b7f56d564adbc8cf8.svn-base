package com.fresh.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.fresh.dao.ShopBaseDao;
import com.fresh.model.Area;
import com.fresh.model.BottomLinks;
import com.fresh.model.Country;
import com.fresh.model.CustomsNews;
import com.fresh.model.Product;
import com.fresh.model.ServiceType;
import com.fresh.model.UsersShow;
import com.nova.frame.utils.JdbcUtils;

public class ShopBaseDaoImpl implements ShopBaseDao {
	/**
	 * 获取所有产品
	 */
	public List<Product> getProduct(){
		List<Product> list = new ArrayList<Product>();
		String sql="SELECT productid,productcode,productcnname,productenname FROM fpdb.product";
		list = JdbcUtils.query(Product.class, sql);
		return list;
	}
	
	
	/**
	 * 获取首页产品
	 */
	public List<Product> getProductIndex(){
		List<Product> list = new ArrayList<Product>();
		String sql="SELECT productid,productcode,productcnname,productenname FROM fpdb.product where indexshow ='Y' order by indexsortshow limit 0,9";
		list = JdbcUtils.query(Product.class, sql);
		return list;
	}
	
	/**
	 * 获取所有地区
	 */
	public List<Area> getArea(){
		List<Area> list = new ArrayList<Area>();
		String sql="SELECT countryareaid,countryareacnname,countryareaenname FROM fpdb.countryarea";
		list = JdbcUtils.query(Area.class, sql);
		return list;
	}
	
	/**
	 * 获取所有国家
	 */
	public List<Country> getCountry(){
		List<Country> list = new ArrayList<Country>();
		String sql="SELECT countryid,countrycode,countrycnname,countryenname FROM fpdb.country";
		list = JdbcUtils.query(Country.class, sql);
		return list;
	}
	
	
	/**
	 * 获取首页国家
	 */
	public List<Country> getCountryIndex(){
		List<Country> list = new ArrayList<Country>();
		String sql="SELECT countryid,countrycode,countrycnname,countryenname FROM fpdb.country where indexshow ='Y' order by indexsortshow limit 0,9";
		list = JdbcUtils.query(Country.class, sql);
		return list;
	}
	
	/**
	 * 获取所有服务类型
	 */
	public List<ServiceType> getServiceType(){
		List<ServiceType> list = new ArrayList<ServiceType>();
		String sql="SELECT servicetypeid,servicetypecode,servicetypecnname,servicetypeenname FROM fpdb.servicetype";
		list = JdbcUtils.query(ServiceType.class, sql);
		return list;
	}
	
	/**
	 * 获取用户的详细信息
	 */
	public List<UsersShow> getUserShowByID(String userID){
		List<UsersShow> list = new ArrayList<UsersShow>();
		String sql="select u.userid,u.logo,u.companyenname,u.content,u.addresscn,u.addressen,u.website,u.telcountry,u.telarea,u.telnum,u.faxcountry,u.faxarea,u.faxnum,u.email,"
				+"p.picvideopath,p.picvideonotes "
				+"from users u "
				+ "left join picvideo p on u.userid= p.userid "
				+ "where u.userid='" + userID + "'";
		list = JdbcUtils.query(UsersShow.class, sql);
		return list;
	}
	
	/**
	 * 获取海关新闻
	 */
	public List<CustomsNews> getCustomsNews(){
		List<CustomsNews> list = new ArrayList<CustomsNews>();
		String sql="SELECT customsnewsid,customsnewscnname,customsnewsenname,customsnewsimgurl,customsnewsaddress,customsnewscontent FROM fpdb.customsnews order by currentdate desc limit 0,2";
		list = JdbcUtils.query(CustomsNews.class, sql);
		return list;
	}
	
	/**
	 * 获取所有链接地址
	 */
	  public List<BottomLinks> getBottomLinks(){
		  List<BottomLinks> list = new ArrayList<BottomLinks>();
		  String sql="select bottomlinksid,bottomlinksname,bottomlinksaddress,bottomlinkstype,indexsortshow from bottomlinks order by bottomlinkstype,indexsortshow";
		  list = JdbcUtils.query(BottomLinks.class, sql);
		  return list;
	}
}
