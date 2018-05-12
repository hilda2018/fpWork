package com.fresh.model;

import java.io.Serializable;

import com.nova.frame.annotation.Id;
import com.nova.frame.annotation.Table;

@Table("customsnews")
public class CustomsNews implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id(auto = false)
	private String customsnewsid;
	private String customsnewscnname;//海关新闻中文名称
	private String customsnewsenname;//海关新闻英文名称
	private String customsnewsimgurl;//海关新闻图片地址
	private String customsnewsaddress;//海关新闻链接地址
	private String customsnewscontent;//海关新闻简介
	public String getCustomsnewsid() {
		return customsnewsid;
	}
	public void setCustomsnewsid(String customsnewsid) {
		this.customsnewsid = customsnewsid;
	}
	public String getCustomsnewscnname() {
		return customsnewscnname;
	}
	public void setCustomsnewscnname(String customsnewscnname) {
		this.customsnewscnname = customsnewscnname;
	}
	public String getCustomsnewsenname() {
		return customsnewsenname;
	}
	public void setCustomsnewsenname(String customsnewsenname) {
		this.customsnewsenname = customsnewsenname;
	}
	public String getCustomsnewsimgurl() {
		return customsnewsimgurl;
	}
	public void setCustomsnewsimgurl(String customsnewsimgurl) {
		this.customsnewsimgurl = customsnewsimgurl;
	}
	public String getCustomsnewsaddress() {
		return customsnewsaddress;
	}
	public void setCustomsnewsaddress(String customsnewsaddress) {
		this.customsnewsaddress = customsnewsaddress;
	}
	public String getCustomsnewscontent() {
		return customsnewscontent;
	}
	public void setCustomsnewscontent(String customsnewscontent) {
		this.customsnewscontent = customsnewscontent;
	}
	public String getCurrentdate() {
		return currentdate;
	}
	public void setCurrentdate(String currentdate) {
		this.currentdate = currentdate;
	}
	private String currentdate;//时间戳
}