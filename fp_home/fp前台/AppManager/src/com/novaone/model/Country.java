package com.novaone.model;

import org.apache.struts2.json.annotations.JSON;

import com.nova.frame.annotation.Id;
import com.nova.frame.annotation.Table;
import com.nova.frame.annotation.Transient;
/**
 * 
 * 国家表实体类
 * @类编号:
 * @类名称:Country
 * @内容摘要: //说明主要功能。
 * @author:陈自军
 * @创建日期:2016年8月11日 下午5:05:24
 * @修改人:
 * @修改日期:
 * @修改描述:简单描述修改的内容
 * @version 1.0.0
 *
 */
@Table("country")
public class Country {
	@Id(auto = false)
	private String countryid;//国家id
	private String countrycode;//国家编号
	@Transient
	private String countryname;//国家名称
	
	private String countrycnname;
	
	private String currentdate;
	@Transient
	private String countryproductid;//国家产品id
	
	public Country() {
	}
	public Country(String countryid, String countrycode, String countryname,
			String currentdate) {
		super();
		this.countryid = countryid;
		this.countrycode = countrycode;
		this.countryname = countryname;
		this.currentdate = currentdate;
	}
	public String getCountryid() {
		return countryid;
	}
	public void setCountryid(String countryid) {
		this.countryid = countryid;
	}
	public String getCountrycode() {
		return countrycode;
	}
	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
	}
	public String getCountryname() {
		return countryname;
	}
	public void setCountryname(String countryname) {
		this.countryname = countryname;
	}
	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public String getCurrentdate() {
		return currentdate;
	}
	public void setCurrentdate(String currentdate) {
		this.currentdate = currentdate;
	}
	public String getCountryproductid() {
		return countryproductid;
	}
	public void setCountryproductid(String countryproductid) {
		this.countryproductid = countryproductid;
	}
	public String getCountrycnname() {
		return countrycnname;
	}
	public void setCountrycnname(String countrycnname) {
		this.countrycnname = countrycnname;
	}
	
	
	
	

}
