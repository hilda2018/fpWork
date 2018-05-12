package com.fresh.model;

import java.io.Serializable;

import com.nova.frame.annotation.Id;
import com.nova.frame.annotation.Table;

@Table("product")
public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 商品ID
	 */
	@Id(auto = false)
	private String _ProductID;
	/**
	 * 商品编号
	 */
	private String _ProductCode;
	/**
	 * 商品名称中文
	 */
	private String _ProductCNName;
	/**
	 * 商品名称英文
	 */
	private String _ProductENName;
	
	
	public String getproductid() {
		return _ProductID;
	}

	public void setproductid(String _ProductID) {
		this._ProductID = _ProductID;
	}
	
	public String getproductcode() {
		return _ProductCode;
	}

	public void setproductcode(String _ProductCode) {
		this._ProductCode = _ProductCode;
	}
	
	public String getproductcnname() {
		return _ProductCNName;
	}

	public void setproductcnname(String _ProductCNName) {
		this._ProductCNName = _ProductCNName;
	}
	
	public String getproductenname() {
		return _ProductENName;
	}

	public void setproductenname(String _ProductENName) {
		this._ProductENName = _ProductENName;
	}
	
}
