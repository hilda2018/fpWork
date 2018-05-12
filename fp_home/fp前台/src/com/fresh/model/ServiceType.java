package com.fresh.model;

import java.io.Serializable;

import com.nova.frame.annotation.Id;
import com.nova.frame.annotation.Table;

@Table("servicetype")
public class ServiceType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 服务类型ID
	 */
	@Id(auto = false)
	private String _ServiceTypeID;
	/**
	 * 服务类型编号
	 */
	private String _ServiceTypeCode;
	/**
	 * 服务类型名称中文
	 */
	private String _ServiceTypeCNName;
	/**
	 * 服务类型名称英文
	 */
	private String _ServiceTypeENName;
	
	
	public String getservicetypeid() {
		return _ServiceTypeID;
	}

	public void setservicetypeid(String _ServiceTypeID) {
		this._ServiceTypeID = _ServiceTypeID;
	}
	
	public String getservicetypecode() {
		return _ServiceTypeCode;
	}

	public void setservicetypecode(String _ServiceTypeCode) {
		this._ServiceTypeCode = _ServiceTypeCode;
	}
	
	public String getservicetypecnname() {
		return _ServiceTypeCNName;
	}

	public void setservicetypecnname(String _ServiceTypeCNName) {
		this._ServiceTypeCNName = _ServiceTypeCNName;
	}
	
	public String getservicetypeenname() {
		return _ServiceTypeENName;
	}

	public void setservicetypeenname(String _ServiceTypeENName) {
		this._ServiceTypeENName = _ServiceTypeENName;
	}
}
