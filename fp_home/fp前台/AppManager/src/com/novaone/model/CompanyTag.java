package com.novaone.model;

import com.nova.frame.annotation.Id;
import com.nova.frame.annotation.Table;

@Table("CompanyTag")
@SuppressWarnings("unused")
public class CompanyTag {
	@Id(auto = false)
	private String companytagid;
	private String companytaginfo;
	private String userid;
	private Integer companytagtype;
	private String currentdate;
	public String getCompanytagid() {
		return companytagid;
	}
	public void setCompanytagid(String companytagid) {
		this.companytagid = companytagid;
	}
	public String getCompanytaginfo() {
		return companytaginfo;
	}
	public void setCompanytaginfo(String companytaginfo) {
		this.companytaginfo = companytaginfo;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public Integer getCompanytagtype() {
		return companytagtype;
	}
	public void setCompanytagtype(Integer companytagtype) {
		this.companytagtype = companytagtype;
	}
	public String getCurrentdate() {
		return currentdate;
	}
	public void setCurrentdate(String currentdate) {
		this.currentdate = currentdate;
	}
	
}
