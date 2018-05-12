package com.fresh.model;

import java.io.Serializable;
import java.util.Date;

import com.nova.frame.annotation.Id;
import com.nova.frame.annotation.Table;
/**
 * 
 * @description 用户申请表 原有用户注册改为用户申请
 * @time 2016年7月12日09:37:57
 * @author wuwenjin
 */
@Table("apply_users")
public class ApplyUsers implements Serializable {


	private static final long serialVersionUID = 1L;
	
	@Id(auto = false)
	private String applyid;
	// 用户名
	private String applyname;
	// 密码
	private String applypwd;
	// 邮箱
	private String applyemail;
	// 公司名称
	private String applycompany;
	// 账户类型
	private String applyservicetype; 
	// 0:未处理  1：已处理
	private int isused;
	// 申请时间
	private Date applydate;
	public String getApplyid() {
		return applyid;
	}
	public void setApplyid(String applyid) {
		this.applyid = applyid;
	}
	public String getApplyname() {
		return applyname;
	}
	public void setApplyname(String applyname) {
		this.applyname = applyname;
	}
	public String getApplypwd() {
		return applypwd;
	}
	public void setApplypwd(String applypwd) {
		this.applypwd = applypwd;
	}
	public String getApplyemail() {
		return applyemail;
	}
	public void setApplyemail(String applyemail) {
		this.applyemail = applyemail;
	}
	public String getApplycompany() {
		return applycompany;
	}
	public void setApplycompany(String applycompany) {
		this.applycompany = applycompany;
	}
	public String getApplyservicetype() {
		return applyservicetype;
	}
	public void setApplyservicetype(String applyservicetype) {
		this.applyservicetype = applyservicetype;
	}
	public int getIsused() {
		return isused;
	}
	public void setIsused(int isused) {
		this.isused = isused;
	}
	public Date getApplydate() {
		return applydate;
	}
	public void setApplydate(Date applydate) {
		this.applydate = applydate;
	}

}
