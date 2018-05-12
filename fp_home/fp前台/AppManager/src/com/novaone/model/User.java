package com.novaone.model;

import java.io.Serializable;

import com.nova.frame.annotation.Id;
import com.nova.frame.annotation.Table;
import com.nova.frame.annotation.Transient;
/**
 * 
 * 子账号表实体类
 * @类编号:
 * @类名称:User
 * @内容摘要: //说明主要功能。
 * @author:陈自军
 * @创建日期:2016年8月11日 下午5:07:38
 * @修改人:
 * @修改日期:
 * @修改描述:简单描述修改的内容
 * @version 1.0.0
 *
 */
@Table("users")
@SuppressWarnings({ "unused", "serial" })
public class User implements Serializable{
	
	@Id(auto = false)
	private String userid;//用户ID
	private String username;//用户名
	private String realcnname;//真实姓名中文
	private String realenname;//真实姓名英文
	private String password;//用户密码
	private String zipcode;//邮编
	private String country;
	private String countrycnname;//国家
	private String countryenname;//国家
	private String countryarea;
	private String countryareacnname;//地区
	private String countryareaenname;

	private String telcountry;//电话国家
	private String telarea;//电话区号
	private String telnum;//电话号码
	private String faxcountry;//传真国家
	private String faxarea;//传真区号
	private String faxnum;//传真号码
	private String email;//电子邮件
	private String skype;//网络电话
	private String wechat;//微信
	private String whatsapp;//
	private String facebook;//
	private String twitter;//
	private String linkin;//
	private String servicetype;
	private String servicetypecnname;//所属服务类型
	private String servicetypeenname;//所属服务类型
	private String companycnname;//公司名称中文
	private String companyenname;//公司名称英文
	private String companyabbreviation;//公司简称
	private String addresscn;//公司地址中文
	private String addressen;//公司地址英文
	private String website;//公司网址
	private String logo;//公司Logo
	private String content;//公司简介
	private Integer enable;//是否启用
	private String  belongmodule;//所属模块
	private String currentdate;//时间戳
	private String buyerproduct;//采购商经营产品
	private String buyerarea;//采购商区域
	//APP添加
	private String phonenum;//手机号码
	private String isapp;//是否是app用户
	private String nickname;//真实姓名
	private Integer markettype;//市场类型
	
	@Transient
	private String primarycncompany;
	@Transient
	private String primaryencompany;
	@Transient
	private String parentid;//主账号id
	
	private Integer defaultprintcount;//默认打印份数
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRealcnname() {
		return realcnname;
	}
	public void setRealcnname(String realcnname) {
		this.realcnname = realcnname;
	}
	public String getRealenname() {
		return realenname;
	}
	public void setRealenname(String realenname) {
		this.realenname = realenname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getTelarea() {
		return telarea;
	}
	public void setTelarea(String telarea) {
		this.telarea = telarea;
	}
	public String getTelnum() {
		return telnum;
	}
	public void setTelnum(String telnum) {
		this.telnum = telnum;
	}
	public String getFaxarea() {
		return faxarea;
	}
	public void setFaxarea(String faxarea) {
		this.faxarea = faxarea;
	}
	public String getFaxnum() {
		return faxnum;
	}
	public void setFaxnum(String faxnum) {
		this.faxnum = faxnum;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSkype() {
		return skype;
	}
	public void setSkype(String skype) {
		this.skype = skype;
	}
	public String getWechat() {
		return wechat;
	}
	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	public String getWhatsapp() {
		return whatsapp;
	}
	public void setWhatsapp(String whatsapp) {
		this.whatsapp = whatsapp;
	}
	public String getFacebook() {
		return facebook;
	}
	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}
	public String getTwitter() {
		return twitter;
	}
	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}
	public String getLinkin() {
		return linkin;
	}
	public void setLinkin(String linkin) {
		this.linkin = linkin;
	}

	public String getServicetypecnname() {
		return servicetypecnname;
	}
	public void setServicetypecnname(String servicetypecnname) {
		this.servicetypecnname = servicetypecnname;
	}
	public String getServicetypeenname() {
		return servicetypeenname;
	}
	public void setServicetypeenname(String servicetypeenname) {
		this.servicetypeenname = servicetypeenname;
	}
	public String getCompanycnname() {
		return companycnname;
	}
	public void setCompanycnname(String companycnname) {
		this.companycnname = companycnname;
	}
	public String getCompanyenname() {
		return companyenname;
	}
	public void setCompanyenname(String companyenname) {
		this.companyenname = companyenname;
	}
	public String getCompanyabbreviation() {
		return companyabbreviation;
	}
	public void setCompanyabbreviation(String companyabbreviation) {
		this.companyabbreviation = companyabbreviation;
	}
	public String getAddresscn() {
		return addresscn;
	}
	public void setAddresscn(String addresscn) {
		this.addresscn = addresscn;
	}
	public String getAddressen() {
		return addressen;
	}
	public void setAddressen(String addressen) {
		this.addressen = addressen;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getEnable() {
		return enable;
	}
	public void setEnable(Integer enable) {
		this.enable = enable;
	}
	public String getCurrentdate() {
		return currentdate;
	}
	public void setCurrentdate(String currentdate) {
		this.currentdate = currentdate;
	}

	public String getCountrycnname() {
		return countrycnname;
	}
	public void setCountrycnname(String countrycnname) {
		this.countrycnname = countrycnname;
	}
	public String getCountryenname() {
		return countryenname;
	}
	public void setCountryenname(String countryenname) {
		this.countryenname = countryenname;
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
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCountryarea() {
		return countryarea;
	}
	public void setCountryarea(String countryarea) {
		this.countryarea = countryarea;
	}
	public String getServicetype() {
		return servicetype;
	}
	public void setServicetype(String servicetype) {
		this.servicetype = servicetype;
	}
	public String getBuyerproduct() {
		return buyerproduct;
	}
	public void setBuyerproduct(String buyerproduct) {
		this.buyerproduct = buyerproduct;
	}
	public String getBuyerarea() {
		return buyerarea;
	}
	public void setBuyerarea(String buyerarea) {
		this.buyerarea = buyerarea;
	}
	public String getTelcountry() {
		return telcountry;
	}
	public void setTelcountry(String telcountry) {
		this.telcountry = telcountry;
	}
	public String getFaxcountry() {
		return faxcountry;
	}
	public void setFaxcountry(String faxcountry) {
		this.faxcountry = faxcountry;
	}
	public String getPhonenum() {
		return phonenum;
	}
	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}
	public String getIsapp() {
		return isapp;
	}
	public void setIsapp(String isapp) {
		this.isapp = isapp;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPrimarycncompany() {
		return primarycncompany;
	}
	public void setPrimarycncompany(String primarycncompany) {
		this.primarycncompany = primarycncompany;
	}
	public String getPrimaryencompany() {
		return primaryencompany;
	}
	public void setPrimaryencompany(String primaryencompany) {
		this.primaryencompany = primaryencompany;
	}
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	/**
	 * markettype
	 *
	 * @return  the markettype
	 * @since   1.0.0
	 */
	
	public Integer getMarkettype() {
		return markettype;
	}
	/**
	 * @param markettype the markettype to set
	 */
	public void setMarkettype(Integer markettype) {
		this.markettype = markettype;
	}
	/**
	 * defaultprintcount
	 *
	 * @return  the defaultprintcount
	 * @since   1.0.0
	 */
	
	public Integer getDefaultprintcount() {
		return defaultprintcount;
	}
	/**
	 * @param defaultprintcount the defaultprintcount to set
	 */
	public void setDefaultprintcount(Integer defaultprintcount) {
		this.defaultprintcount = defaultprintcount;
	}
	/**
	 * belongmodule
	 *
	 * @return  the belongmodule
	 * @since   1.0.0
	 */
	
	public String getBelongmodule() {
		return belongmodule;
	}
	/**
	 * @param belongmodule the belongmodule to set
	 */
	public void setBelongmodule(String belongmodule) {
		this.belongmodule = belongmodule;
	}
}
