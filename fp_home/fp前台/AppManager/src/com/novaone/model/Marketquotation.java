package com.novaone.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import com.nova.frame.annotation.Id;
import com.nova.frame.annotation.Table;
import com.nova.frame.annotation.Transient;

/**
 * 价格行情表实体类
 * 
 * @类编号:
 * @类名称:Marketquotation
 * @内容摘要: //说明主要功能。
 * @author:陈自军
 * @创建日期:2016年8月11日 下午5:13:28
 * @修改人:
 * @修改日期:
 * @修改描述:简单描述修改的内容
 * @version 1.0.0
 *
 */
@SuppressWarnings("serial")
@Table("marketquotation")
public class Marketquotation implements Serializable {
	@Id(auto=false)
	private String mqId;//市场行情ID
	private String enteruserid;//录入人
	@Transient
	//当记录不为转发时,显示为录入人姓名,当记录为转发时,显示为转发人姓名
	private String  enterusername;//录入人姓名(昵称)
	
	private String enterdate;//录入日期
	private Integer markettype;//市场类型 '0:辉展市场;1:江南市场'
	private String marketdate;//市场日期
	private String country;//国家id
	@Transient
	private String countryname;//国家名称
	@Transient
	private String countryenname;
	@Transient
	private String productname;//商品名称
	@Transient
	private String productenname;
	
	private String countryproductid;//国家商品id
	private String product;//品名id
	private String varieties;//品种
	private String spec;//规格
	private String supplier;//销售商
	private String vendor;//供应商
	private String brand;//品牌
	private String weight;//重量
	private  Integer transport;//运输方式 0:海运;1:空运;2:陆运
	private String packagetype;//包装
	private BigDecimal minprice;//最低价格 decimal(10,2)
	private BigDecimal maxprice;//最高价格  decimal(10,2)
	private String quality;//品质
	private String sailedescribe;//销售描述	
	private BigDecimal platenum;//板数 decimal(10,1)
	private Integer istransmit;//是否转发 0:非转发;1:转发'
	private String transmituser;//转发人
	private String currentdate;//时间戳
	private String modifydate;//修改时间
	private String status;//记录状态 0记录转发没有确认,1:记录转发已确认,记录录入默认状态
	//modify by chenzijun 2016-07-21
	private String cntrno;//柜号
	private String packingplant;//包装厂
	private String farm;//果园
	private String currencyid;//币别
	//modify by chenzijun 2016-08-16
	private String mergeid;//合并id
	//add newold 2016-10-27
	private String newold;//新旧
	@Transient
	private String datastate;//记录状态:0 全部 1自己录入的
	
	@Transient
	private List<Mqpicture> piclist;//图片

	@Transient
	private String startTime;//开始日期
	@Transient
	private String endTime;//结束日期
	@Transient
	private String currencycnname;//币别中文名称
	@Transient
	private String currencyenname;//币别英文名称
	@Transient
	private String parentid;//录入人主账号id
	@Transient
	private List<MQDetail> mqdetail;//多品牌多品名集合
	
	public Marketquotation() {
	}

	public Marketquotation(String mqId, String enteruserid, String enterdate,
			Integer markettype, String marketdate, String country,
			String product, String varieties, String spec, String supplier,
			String vendor, String brand, String weight, Integer transport,
			String packagetype, BigDecimal minprice, BigDecimal maxprice,
			String quality, String sailedescribe, BigDecimal platenum,
			Integer istransmit, String transmituser, String currentdate) {
		super();
		this.mqId = mqId;
		this.enteruserid = enteruserid;
		this.enterdate = enterdate;
		this.markettype = markettype;
		this.marketdate = marketdate;
		this.country = country;
		this.product = product;
		this.varieties = varieties;
		this.spec = spec;
		this.supplier = supplier;
		this.vendor = vendor;
		this.brand = brand;
		this.weight = weight;
		this.transport = transport;
		this.packagetype = packagetype;
		this.minprice = minprice;
		this.maxprice = maxprice;
		this.quality = quality;
		this.sailedescribe = sailedescribe;
		this.platenum = platenum;
		this.istransmit = istransmit;
		this.transmituser = transmituser;
		this.currentdate = currentdate;
	}

	public String getMqId() {
		return mqId;
	}

	public void setMqId(String mqId) {
		this.mqId = mqId;
	}

	public String getEnteruserid() {
		return enteruserid;
	}

	public void setEnteruserid(String enteruserid) {
		this.enteruserid = enteruserid;
	}
	@JSON(format = "yyyy-MM-dd")
	public String getEnterdate() {
		return enterdate;
	}

	public void setEnterdate(String enterdate) {
		this.enterdate = enterdate;
	}

	public Integer getMarkettype() {
		return markettype;
	}

	public void setMarkettype(Integer markettype) {
		this.markettype = markettype;
	}
	@JSON(format = "yyyy/MM/dd")
	public String getMarketdate() {
		return marketdate;
	}
	@JSON(format = "yyyy/MM/dd")
	public void setMarketdate(String marketdate) {
		this.marketdate =marketdate;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
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

	public Integer getTransport() {
		return transport;
	}

	public void setTransport(Integer transport) {
		this.transport = transport;
	}

	public String getPackagetype() {
		return packagetype;
	}

	public void setPackagetype(String packagetype) {
		this.packagetype = packagetype;
	}

	public BigDecimal getMinprice() {
		return minprice;
	}

	public void setMinprice(BigDecimal minprice) {
		this.minprice = minprice;
	}

	public BigDecimal getMaxprice() {
		return maxprice;
	}

	public void setMaxprice(BigDecimal maxprice) {
		this.maxprice = maxprice;
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

	public Integer getIstransmit() {
		return istransmit;
	}

	public void setIstransmit(Integer istransmit) {
		this.istransmit = istransmit;
	}

	public String getTransmituser() {
		return transmituser;
	}

	public void setTransmituser(String transmituser) {
		this.transmituser = transmituser;
	}
	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public String getCurrentdate() {
		return currentdate;
	}

	public void setCurrentdate(String currentdate) {
		this.currentdate = currentdate;
	}

	public List<Mqpicture> getPiclist() {
		return piclist;
	}

	public void setPiclist(List<Mqpicture> piclist) {
		this.piclist = piclist;
	}
	@JSON(format = "yyyy-MM-dd")
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	@JSON(format = "yyyy-MM-dd")
	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public String getModifydate() {
		return modifydate;
	}

	public void setModifydate(String modifydate) {
		this.modifydate = modifydate;
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCountryname() {
		return countryname;
	}

	public void setCountryname(String countryname) {
		this.countryname = countryname;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public String getCountryproductid() {
		return countryproductid;
	}

	public void setCountryproductid(String countryproductid) {
		this.countryproductid = countryproductid;
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

	public String getCurrencyid() {
		return currencyid;
	}

	public void setCurrencyid(String currencyid) {
		this.currencyid = currencyid;
	}

	public String getCountryenname() {
		return countryenname;
	}

	public void setCountryenname(String countryenname) {
		this.countryenname = countryenname;
	}

	public String getProductenname() {
		return productenname;
	}

	public void setProductenname(String productenname) {
		this.productenname = productenname;
	}

	public String getCurrencycnname() {
		return currencycnname;
	}

	public void setCurrencycnname(String currencycnname) {
		this.currencycnname = currencycnname;
	}

	public String getCurrencyenname() {
		return currencyenname;
	}

	public void setCurrencyenname(String currencyenname) {
		this.currencyenname = currencyenname;
	}

	public BigDecimal getPlatenum() {
		return platenum;
	}

	public void setPlatenum(BigDecimal platenum) {
		this.platenum = platenum;
	}


	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public String getEnterusername() {
		return enterusername;
	}

	public void setEnterusername(String enterusername) {
		this.enterusername = enterusername;
	}

	/**
	 * mergeid
	 *
	 * @return  the mergeid
	 * @since   1.0.0
	 */
	
	public String getMergeid() {
		return mergeid;
	}

	/**
	 * @param mergeid the mergeid to set
	 */
	public void setMergeid(String mergeid) {
		this.mergeid = mergeid;
	}

	/**
	 * mqdetail
	 *
	 * @return  the mqdetail
	 * @since   1.0.0
	 */
	
	public List<MQDetail> getMqdetail() {
		return mqdetail;
	}

	/**
	 * @param mqdetail the mqdetail to set
	 */
	public void setMqdetail(List<MQDetail> mqdetail) {
		this.mqdetail = mqdetail;
	}

	/**
	 * datastate
	 *
	 * @return  the datastate
	 * @since   1.0.0
	 */
	
	public String getDatastate() {
		return datastate;
	}

	/**
	 * @param datastate the datastate to set
	 */
	public void setDatastate(String datastate) {
		this.datastate = datastate;
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

	
}
