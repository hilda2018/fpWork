package com.novaone.model;

import java.io.Serializable;
import java.math.BigDecimal;
/**
 * 
 * 价格录入关于多品种品牌实体类
 * @类编号:
 * @类名称:MQDetail
 * @内容摘要: //说明主要功能。
 * @author:陈自军
 * @创建日期:2016年8月17日 下午4:46:23
 * @修改人:
 * @修改日期:
 * @修改描述:简单描述修改的内容
 * @version 1.0.0
 *
 */
public class MQDetail implements Serializable{
	
	private static final long serialVersionUID = -4761023278454147008L;
	private String brand;//品牌
	private String varieties;//品种
	private String spec;//规格
	private BigDecimal minprice;//最低价格 decimal(10,2)
	private BigDecimal maxprice;//最高价格  decimal(10,2)
	private BigDecimal platenum;//板数 decimal(10,1)
	
	
	
	public MQDetail() {
		super();
	}
	
	public MQDetail(String brand, String varieties, String spec,
			BigDecimal minprice, BigDecimal maxprice) {
		super();
		this.brand = brand;
		this.varieties = varieties;
		this.spec = spec;
		this.minprice = minprice;
		this.maxprice = maxprice;
	}

	public MQDetail(String brand, String varieties, String spec,
			BigDecimal minprice, BigDecimal maxprice,BigDecimal platenum) {
		super();
		this.brand = brand;
		this.varieties = varieties;
		this.spec = spec;
		this.minprice = minprice;
		this.maxprice = maxprice;
		this.platenum = platenum;
	}

	/**
	 * brand
	 *
	 * @return  the brand
	 * @since   1.0.0
	 */
	
	public String getBrand() {
		return brand;
	}
	/**
	 * @param brand the brand to set
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}
	/**
	 * varieties
	 *
	 * @return  the varieties
	 * @since   1.0.0
	 */
	
	public String getVarieties() {
		return varieties;
	}
	/**
	 * @param varieties the varieties to set
	 */
	public void setVarieties(String varieties) {
		this.varieties = varieties;
	}
	/**
	 * spec
	 *
	 * @return  the spec
	 * @since   1.0.0
	 */
	
	public String getSpec() {
		return spec;
	}
	/**
	 * @param spec the spec to set
	 */
	public void setSpec(String spec) {
		this.spec = spec;
	}
	/**
	 * minprice
	 *
	 * @return  the minprice
	 * @since   1.0.0
	 */
	
	public BigDecimal getMinprice() {
		return minprice;
	}
	/**
	 * @param minprice the minprice to set
	 */
	public void setMinprice(BigDecimal minprice) {
		this.minprice = minprice;
	}
	/**
	 * maxprice
	 *
	 * @return  the maxprice
	 * @since   1.0.0
	 */
	
	public BigDecimal getMaxprice() {
		return maxprice;
	}
	/**
	 * @param maxprice the maxprice to set
	 */
	public void setMaxprice(BigDecimal maxprice) {
		this.maxprice = maxprice;
	}

	/**
	 * platenum
	 *
	 * @return  the platenum
	 * @since   1.0.0
	 */
	
	public BigDecimal getPlatenum() {
		return platenum;
	}

	/**
	 * @param platenum the platenum to set
	 */
	public void setPlatenum(BigDecimal platenum) {
		this.platenum = platenum;
	}
	
	

}
