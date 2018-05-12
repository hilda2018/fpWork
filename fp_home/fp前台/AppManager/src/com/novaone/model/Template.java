
package com.novaone.model;

import java.io.Serializable;

import com.nova.frame.annotation.Id;
import com.nova.frame.annotation.Table;
import com.nova.frame.annotation.Transient;

/**
 * 
 * 市场行情APP:国家产品模板表实体类
 * @类编号:
 * @类名称:Template
 * @内容摘要: //说明主要功能。
 * @author:陈自军
 * @创建日期:2016年8月11日 下午5:08:06
 * @修改人:
 * @修改日期:
 * @修改描述:简单描述修改的内容
 * @version 1.0.0
 *
 */
@Table("template")
public class Template implements Serializable {
	private static final long serialVersionUID = 7950773715380260821L;
	@Id(auto=false)
	private String id;
	private String userid;
	private String productid;//商品id
	private String countryid;//国家id
	private String varieties;	//品种	
	private String spec;	//规格
	private String supplier;//销售商
	private String vendor;//供应商	
	private String brand;	//品牌
	private String weight;	//重量	
	private String transport;	//运输方式
	private String packagetype;	//包装
	private String price;	//价格	
	private String quality;	//品质
	private String sailedescribe;	//销售描述
	private String platenum;	//板数
	private String cntrno;//柜号
	private String packingplant;//包装厂
	private String farm;//果园
	private String newold;//新旧
	private String pic;//图片
	//private Date currentdate;//创建时间
	@Transient
	private String productname;//商品名称
	@Transient
	private String countryname;//国家名称
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProductid() {
		return productid;
	}
	public void setProductid(String productid) {
		this.productid = productid;
	}
	public String getCountryid() {
		return countryid;
	}
	public void setCountryid(String countryid) {
		this.countryid = countryid;
	}
	public String getVarieties() {
		return varieties;
	}
	public void setVarieties(String varieties) {
		this.varieties = varieties;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getTransport() {
		return transport;
	}
	public void setTransport(String transport) {
		this.transport = transport;
	}
	public String getPackagetype() {
		return packagetype;
	}
	public void setPackagetype(String packagetype) {
		this.packagetype = packagetype;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getQuality() {
		return quality;
	}
	public void setQuality(String quality) {
		this.quality = quality;
	}
	public String getSailedescribe() {
		return sailedescribe;
	}
	public void setSailedescribe(String sailedescribe) {
		this.sailedescribe = sailedescribe;
	}
	public String getPlatenum() {
		return platenum;
	}
	public void setPlatenum(String platenum) {
		this.platenum = platenum;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public String getCountryname() {
		return countryname;
	}
	public void setCountryname(String countryname) {
		this.countryname = countryname;
	}
	public String getCntrno() {
		return cntrno;
	}
	public void setCntrno(String cntrno) {
		this.cntrno = cntrno;
	}
	public String getPackingplant() {
		return packingplant;
	}
	public void setPackingplant(String packingplant) {
		this.packingplant = packingplant;
	}
	public String getFarm() {
		return farm;
	}
	public void setFarm(String farm) {
		this.farm = farm;
	}
	/**
	 * newold
	 *
	 * @return  the newold
	 * @since   1.0.0
	 */
	
	public String getNewold() {
		return newold;
	}
	/**
	 * @param newold the newold to set
	 */
	public void setNewold(String newold) {
		this.newold = newold;
	}
	/**
	 * pic
	 *
	 * @return  the pic
	 * @since   1.0.0
	 */
	
	public String getPic() {
		return pic;
	}
	/**
	 * @param pic the pic to set
	 */
	public void setPic(String pic) {
		this.pic = pic;
	}
}
