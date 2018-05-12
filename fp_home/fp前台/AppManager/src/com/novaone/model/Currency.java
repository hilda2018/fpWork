package com.novaone.model;

import java.io.Serializable;

import com.nova.frame.annotation.Id;
import com.nova.frame.annotation.Table;
/**
 * 货币币别表实体类
 * 
 * @类编号:
 * @类名称:Currency
 * @内容摘要: //说明主要功能。
 * @author:陈自军
 * @创建日期:2016年8月11日 下午5:13:53
 * @修改人:
 * @修改日期:
 * @修改描述:简单描述修改的内容
 * @version 1.0.0
 *
 */
@Table("currency")
public class Currency implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id(auto=false)
	private String currencyid;
	private String currencycode;
	private String currencycnname;//中文名称
	private String currencyenname;//英文名称
	public String getCurrencyid() {
		return currencyid;
	}
	public void setCurrencyid(String currencyid) {
		this.currencyid = currencyid;
	}
	public String getCurrencycode() {
		return currencycode;
	}
	public void setCurrencycode(String currencycode) {
		this.currencycode = currencycode;
	}
	public String getCurrencycnname() {
		return currencycnname;
	}
	public void setCurrencycnname(String currencycnname) {
		this.currencycnname = currencycnname;
	}
	public String getCurrencyenname() {
		return currencyenname;
	}
	public void setCurrencyenname(String currencyenname) {
		this.currencyenname = currencyenname;
	}
	
	
	

}
