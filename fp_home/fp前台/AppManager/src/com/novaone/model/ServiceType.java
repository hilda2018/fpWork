package com.novaone.model;

import com.nova.frame.annotation.Id;
import com.nova.frame.annotation.Table;

@Table("servicetype")
@SuppressWarnings("unused")
public class ServiceType {

	@Id(auto = false)
	private String servicetypeid;
	private String servicetypecode;
	private String servicetypecnname;
	private String servicetypeenname;
	private String currentdate;
	public String getServicetypeid() {
		return servicetypeid;
	}
	public void setServicetypeid(String servicetypeid) {
		this.servicetypeid = servicetypeid;
	}
	public String getServicetypecode() {
		return servicetypecode;
	}
	public void setServicetypecode(String servicetypecode) {
		this.servicetypecode = servicetypecode;
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
	public String getCurrentdate() {
		return currentdate;
	}
	public void setCurrentdate(String currentdate) {
		this.currentdate = currentdate;
	}
}
