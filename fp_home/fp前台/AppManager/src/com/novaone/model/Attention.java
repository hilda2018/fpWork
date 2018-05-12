package com.novaone.model;

import java.io.Serializable;

import com.nova.frame.annotation.Id;
import com.nova.frame.annotation.Table;
import com.nova.frame.annotation.Transient;

/**
 * 价格行情:用户关注品名表实体类
 * 
 * @类编号:
 * @类名称:Attention
 * @内容摘要: //说明主要功能。
 * @author:陈自军
 * @创建日期:2016年8月11日 下午5:12:57
 * @修改人:
 * @修改日期:
 * @修改描述:简单描述修改的内容
 * @version 1.0.0
 *
 */
@Table("attention")
public class Attention implements Serializable {
	private static final long serialVersionUID = -4882578366989698331L;
	@Id(auto=false)
	private String id;
	private String userid;//用户id
	private String productid;//品种id
	private String currentdate;
	@Transient
	private String productname;//品种名称
	@Transient
	private String initial;//首字母
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getProductid() {
		return productid;
	}
	public void setProductid(String productid) {
		this.productid = productid;
	}
	public String getCurrentdate() {
		return currentdate;
	}
	public void setCurrentdate(String currentdate) {
		this.currentdate = currentdate;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public String getInitial() {
		return initial;
	}
	public void setInitial(String initial) {
		this.initial = initial;
	}
	
}
