package com.novaone.model.system;

import com.nova.frame.annotation.Id;
import com.nova.frame.annotation.Table;

import java.io.Serializable;

@Table("d_org")
public class Org implements Serializable{

	private static final long serialVersionUID = -8339003873278068359L;
	@Id(auto = false)
	private String id;
	private String code;
	private String name;
	private String description;
	private String parentid;
	private String sortindex;
	private String partcode;
	private String isleaf;

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

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public String getSortindex() {
		return sortindex;
	}

	public void setSortindex(String sortindex) {
		this.sortindex = sortindex;
	}

	public String getPartcode() {
		return partcode;
	}

	public void setPartcode(String partcode) {
		this.partcode = partcode;
	}

	public String getIsleaf() {
		return isleaf;
	}

	public void setIsleaf(String isleaf) {
		this.isleaf = isleaf;
	}
}
