package com.novaone.model;

import java.io.Serializable;

import com.nova.frame.annotation.Id;
import com.nova.frame.annotation.Table;
/**
 * 
 * 国家产品表实体类
 * @类编号:
 * @类名称:CountryProduct
 * @内容摘要: //说明主要功能。
 * @author:陈自军
 * @创建日期:2016年8月11日 下午5:11:39
 * @修改人:
 * @修改日期:
 * @修改描述:简单描述修改的内容
 * @version 1.0.0
 *
 */
@SuppressWarnings("serial")
@Table("countryproduct")
public class CountryProduct implements Serializable {
	@Id(auto=false)
	private String countryproductid;
	private String countryid;
	private String productid;
	public String getCountryproductid() {
		return countryproductid;
	}
	public void setCountryproductid(String countryproductid) {
		this.countryproductid = countryproductid;
	}
	public String getCountryid() {
		return countryid;
	}
	public void setCountryid(String countryid) {
		this.countryid = countryid;
	}
	public String getProductid() {
		return productid;
	}
	public void setProductid(String productid) {
		this.productid = productid;
	}
	
}
