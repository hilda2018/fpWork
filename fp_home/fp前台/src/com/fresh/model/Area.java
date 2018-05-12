package com.fresh.model;

import java.io.Serializable;

import com.nova.frame.annotation.Id;
import com.nova.frame.annotation.Table;

@Table("countryarea")
public class Area implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 地区ID
	 */
	@Id(auto = false)
	private String _CountryAreaID;
	/**
	 * 地区中文
	 */
	private String _CountryAreaCNName;
	/**
	 * 地区英文
	 */
	private String _CountryAreaENName;
	
	
	public String getcountryareaid() {
		return _CountryAreaID;
	}

	public void setcountryareaid(String _CountryAreaID) {
		this._CountryAreaID = _CountryAreaID;
	}
	
	
	public String getcountryareacnname() {
		return _CountryAreaCNName;
	}

	public void setcountryareacnname(String _CountryAreaCNName) {
		this._CountryAreaCNName = _CountryAreaCNName;
	}
	
	public String getcountryareaenname() {
		return _CountryAreaENName;
	}

	public void setcountryareaenname(String _CountryAreaENName) {
		this._CountryAreaENName = _CountryAreaENName;
	}
	
	
}
