package com.fresh.model;

import java.io.Serializable;

import com.nova.frame.annotation.Id;
import com.nova.frame.annotation.Table;

@Table("users")
public class UsersShow implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 用户ID
	 */
	@Id(auto = false)
	private String _UserID;
	/**
	 * 公司Logo
	 */
	private String _Logo;
	/**
	 * 公司名称
	 */
	private String _CompanyEnname;
	/**
	 * 公司简介
	 */
	private String _Content;
	/**
	 * 公司地址中文
	 */
	private String _AddressCN;
	/**
	 * 公司地址英文
	 */
	private String _AddressEN;
	/**
	 * 公司网址
	 */
	private String _WebSite;
	/**
	 * 电话国家
	 */
	private String _TelCountry;
	/**
	 * 电话区号
	 */
	private String _TelArea;
	/**
	 * 电话号码
	 */
	private String _TelNum;
	/**
	 * 传真国家
	 */
	private String _FaxCountry;
	/**
	 * 传真区号
	 */
	private String _FaxArea;
	/**
	 * 传真号码
	 */
	private String _FaxNum;
	/**
	 * 邮箱
	 */
	private String _Email;
	/**
	 * 图片视频地址
	 */
	private String _PicVideoPath;
	/**
	 * 图片视频简介
	 */
	private String _PicVideoNotes;
	
	public String getuserid() {
		return _UserID;
	}

	public void setuserid(String _UserID) {
		this._UserID = _UserID;
	}

	public String getlogo() {
		return _Logo;
	}

	public void setlogo(String _Logo) {
		this._Logo = _Logo;
	}
	public String getcompanyenname() {
		return _CompanyEnname;
	}

	public void setcompanyenname(String _CompanyEnname) {
		this._CompanyEnname = _CompanyEnname;
	}
	public String getcontent() {
		return _Content;
	}

	public void setcontent(String _Content) {
		this._Content = _Content;
	}
	
	public String getaddresscn() {
		return _AddressCN;
	}

	public void setaddresscn(String _AddressCN) {
		this._AddressCN = _AddressCN;
	}
	
	public String getaddressen() {
		return _AddressEN;
	}

	public void setaddressen(String _AddressEN) {
		this._AddressEN = _AddressEN;
	}
	
	public String getwebsite() {
		return _WebSite;
	}

	public void setwebsite(String _WebSite) {
		this._WebSite = _WebSite;
	}
	
	public String gettelcountry() {
		return _TelCountry;
	}

	public void settelcountry(String _TelCountry) {
		this._TelCountry = _TelCountry;
	}
	
	public String gettelarea() {
		return _TelArea;
	}

	public void settelarea(String _TelArea) {
		this._TelArea = _TelArea;
	}
	
	public String gettelnum() {
		return _TelNum;
	}

	public void settelnum(String _TelNum) {
		this._TelNum = _TelNum;
	}
	
	public String getfaxcountry() {
		return _FaxCountry;
	}

	public void setfaxcountry(String _FaxCountry) {
		this._FaxCountry = _FaxCountry;
	}
	
	public String getfaxarea() {
		return _FaxArea;
	}

	public void setfaxarea(String _FaxArea) {
		this._FaxArea = _FaxArea;
	}
	
	public String getfaxnum() {
		return _FaxNum;
	}

	public void setfaxnum(String _FaxNum) {
		this._FaxNum = _FaxNum;
	}
	
	public String getemail() {
		return _Email;
	}

	public void setemail(String _Email) {
		this._Email = _Email;
	}
	
	public String getpicvideopath() {
		return _PicVideoPath;
	}

	public void setpicvideopath(String _PicVideoPath) {
		this._PicVideoPath = _PicVideoPath;
	}
	
	public String getpicvideonotes() {
		return _PicVideoNotes;
	}

	public void setpicvideonotes(String _PicVideoNotes) {
		this._PicVideoNotes = _PicVideoNotes;
	}
}
