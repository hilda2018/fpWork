package com.novaone.model;

import java.io.Serializable;

import com.nova.frame.annotation.Id;
import com.nova.frame.annotation.Table;

@Table("d_usermenu")
public class DUserMenu implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id(auto = false)
	private String id;
	
	private String menuid;
	
	private String userid;
	
	private String isenable;
	
	
	public DUserMenu() {
	}
	

	public DUserMenu(String id, String menuid, String userid, String isenable) {
		super();
		this.id = id;
		this.menuid = menuid;
		this.userid = userid;
		this.isenable = isenable;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMenuid() {
		return menuid;
	}

	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getIsenable() {
		return isenable;
	}

	public void setIsenable(String isenable) {
		this.isenable = isenable;
	}
}

