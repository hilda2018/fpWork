package com.novaone.model;

import java.io.Serializable;

import com.nova.frame.annotation.Id;
import com.nova.frame.annotation.Table;
import com.nova.frame.annotation.Transient;

@Table("d_user")
/**
 * 用户登录表实体类
 * 
 * @类编号:
 * @类名称:DUser
 * @内容摘要: //说明主要功能。
 * @author:陈自军
 * @创建日期:2016年8月11日 下午5:07:10
 * @修改人:
 * @修改日期:
 * @修改描述:简单描述修改的内容
 * @version 1.0.0
 *
 */
public class DUser implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id(auto = false)
	private String id;
	private String code;//登录名称
	
	private String name;//姓名
	
	private String password;//密码
	
	private String isusing;//是否可用
	private String parentid;
    @Transient
    private String markettype;//市场类型0:辉展市场;1:江南市场
    
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIsusing() {
		return isusing;
	}

	public void setIsusing(String isusing) {
		this.isusing = isusing;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}


	public String getMarkettype() {
		return markettype;
	}

	public void setMarkettype(String markettype) {
		this.markettype = markettype;
	}


}

