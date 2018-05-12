package com.fresh.model;

import java.io.Serializable;

import com.nova.frame.annotation.Id;
import com.nova.frame.annotation.Table;

@Table("users")
public class UserRegis implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 用户ID
	 */
	@Id(auto = false)
	private String userid;
	
	public String getuserid() {
		return userid;
	}

	public void setuserid(String userid) {
		this.userid = userid;
	}
	
	/**
	 * 用户名
	 */
	private String username;
	
	public String getusername() {
		return username;
	}

	public void setusername(String username) {
		this.username = username;
	}
	
	/**
	 * 用户密码
	 */
	private String password;
	
	public String getpassword() {
		return password;
	}

	public void setpassword(String password) {
		this.password = password;
	}
	
	/**
	 * 邮箱
	 */
	private String email;
	
	public String getemail() {
		return email;
	}

	public void setemail(String email) {
		this.email = email;
	}
	
	/**
	 * 所属模块
	 */
	private Integer belongmodule;
	
	public Integer getbelongmodule() {
		return belongmodule;
	}

	public void setbelongmodule(Integer belongmodule) {
		this.belongmodule = belongmodule;
	}
	
	private String companycnname;
	private String companyenname;

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
}
