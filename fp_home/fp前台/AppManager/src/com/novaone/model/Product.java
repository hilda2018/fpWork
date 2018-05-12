/**
 * 
 */
package com.novaone.model;

import org.apache.struts2.json.annotations.JSON;

import com.nova.frame.annotation.Id;
import com.nova.frame.annotation.Table;
import com.nova.frame.annotation.Transient;

/**
 * 
 * 产品表实体类
 * @类编号:
 * @类名称:Product
 * @内容摘要: //说明主要功能。
 * @author:陈自军
 * @创建日期:2016年8月11日 下午5:05:51
 * @修改人:
 * @修改日期:
 * @修改描述:简单描述修改的内容
 * @version 1.0.0
 *
 */
@SuppressWarnings("serial")
@Table("product")
public class Product {
	@Id(auto=false)
	private String productid;//商品id
	private String productcode;//商品编号
	private String productcnname;//商品名称
	@Transient
	private String productname;
	
	private String currentdate;
	@Transient
	private String status;//关注状态
	public Product() {
	}
	public Product(String productid, String productcode, String productname,
			String currentdate) {
		super();
		this.productid = productid;
		this.productcode = productcode;
		this.productname = productname;
		this.currentdate = currentdate;
	}
	public String getProductid() {
		return productid;
	}
	public void setProductid(String productid) {
		this.productid = productid;
	}
	public String getProductcode() {
		return productcode;
	}
	public void setProductcode(String productcode) {
		this.productcode = productcode;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
    @JSON(format = "yyyy-MM-dd HH:mm:ss")
	public String getCurrentdate() {
		return currentdate;
	}
	public void setCurrentdate(String currentdate) {
		this.currentdate = currentdate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getProductcnname() {
		return productcnname;
	}
	public void setProductcnname(String productcnname) {
		this.productcnname = productcnname;
	}
	
}
