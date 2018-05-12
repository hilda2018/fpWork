package com.novaone.model;

import com.nova.frame.annotation.Id;
import com.nova.frame.annotation.Table;
/**
 * 国家地区
 * @author 刘辉
 *
 */
@Table("countryarea")
@SuppressWarnings("unused")
public class CountryArea {

	@Id(auto = false)
	private String countryareaid;
	private String countryid;
	private String countryareacnname;
	private String countryareaenname;
	private String currentdate;
	public String getCountryareaid() {
		return countryareaid;
	}
	public void setCountryareaid(String countryareaid) {
		this.countryareaid = countryareaid;
	}
	public String getCountryid() {
		return countryid;
	}
	public void setCountryid(String countryid) {
		this.countryid = countryid;
	}
	public String getCountryareacnname() {
		return countryareacnname;
	}
	public void setCountryareacnname(String countryareacnname) {
		this.countryareacnname = countryareacnname;
	}
	public String getCountryareaenname() {
		return countryareaenname;
	}
	public void setCountryareaenname(String countryareaenname) {
		this.countryareaenname = countryareaenname;
	}
	public String getCurrentdate() {
		return currentdate;
	}
	public void setCurrentdate(String currentdate) {
		this.currentdate = currentdate;
	}
	
}
