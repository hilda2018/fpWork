/**
 * 
 */
package com.novaone.model;

import java.io.Serializable;

import org.apache.struts2.json.annotations.JSON;

import com.nova.frame.annotation.Id;
import com.nova.frame.annotation.Table;

/**
 * 
 * 价格行情 常用信息统计表实体类
 * @类编号:
 * @类名称:Cominfo
 * @内容摘要: //说明主要功能。
 * @author:陈自军
 * @创建日期:2016年8月11日 下午5:12:13
 * @修改人:
 * @修改日期:
 * @修改描述:简单描述修改的内容
 * @version 1.0.0
 *
 */
@SuppressWarnings("serial")
@Table("cominfo")
public class Cominfo implements Serializable {
	
	@Id(auto=false)
	private String cominfoid;
	private String username;//用户名
	//字段类型0:国家;1:品名;2:品种;3:规格;4:销售商;5:供应商;6:品牌;7:重量;8:包装;9:品质;10:销售描述;11:板数
	private String country;//国家
	private String product;//产品
	private Integer fieldtype;
	private String fieldvalue;
	private Integer times;
	private String currentdate;
	private String lastdate;//最后修改时间
	private String initial;//大写首字母
	private String parentid;//主账号id
	
	public Cominfo() {
	}
	public Cominfo(String cominfoid, String username, Integer fieldtype,
			String fieldvalue, Integer times, String currentdate) {
		super();
		this.cominfoid = cominfoid;
		this.username = username;
		this.fieldtype = fieldtype;
		this.fieldvalue = fieldvalue;
		this.times = times;
		this.currentdate = currentdate;
	}
	public String getCominfoid() {
		return cominfoid;
	}
	public void setCominfoid(String cominfoid) {
		this.cominfoid = cominfoid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getFieldtype() {
		return fieldtype;
	}
	public void setFieldtype(Integer fieldtype) {
		this.fieldtype = fieldtype;
	}
	public String getFieldvalue() {
		return fieldvalue;
	}
	public void setFieldvalue(String fieldvalue) {
		this.fieldvalue = fieldvalue;
	}
	public Integer getTimes() {
		return times;
	}
	public void setTimes(Integer times) {
		this.times = times;
	}
    @JSON(format = "yyyy-MM-dd HH:mm:ss")
	public String getCurrentdate() {
		return currentdate;
	}
	public void setCurrentdate(String currentdate) {
		this.currentdate = currentdate;
	}
	public String getLastdate() {
		return lastdate;
	}
	public void setLastdate(String lastdate) {
		this.lastdate = lastdate;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getInitial() {
		return initial;
	}
	public void setInitial(String initial) {
		this.initial = initial;
	}
	/**
	 * parentid
	 *
	 * @return  the parentid
	 * @since   1.0.0
	 */
	
	public String getParentid() {
		return parentid;
	}
	/**
	 * @param parentid the parentid to set
	 */
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	
	

}
