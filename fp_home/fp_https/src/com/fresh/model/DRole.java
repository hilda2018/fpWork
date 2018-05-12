package com.fresh.model;

import com.nova.frame.annotation.Id;
import com.nova.frame.annotation.Table;

/**
 * DRole entity. @author MyEclipse Persistence Tools
 */
@Table("d_role")
public class DRole implements java.io.Serializable {

	// Fields
	@Id(auto = false)
	private String id;
	private String code;
	private String companytype;
	private String name;
	private String description;
	
	private String isyz;

	// Constructors

	/** default constructor */
	public DRole() {
	}

	/** minimal constructor */
	public DRole(String id, String code, String name) {
		this.id = id;
		this.code = code;
		this.name = name;
	}

	/** full constructor */
	public DRole(String id, String code, String name, String description) {
		this.id = id;
		this.code = code;
		this.name = name;
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIsyz() {
	
		return isyz;
	}

	public void setIsyz(String isyz) {
	
		this.isyz = isyz;
	}

	public String getCompanytype() {
		return companytype;
	}

	public void setCompanytype(String companytype) {
		this.companytype = companytype;
	}

	


	
}