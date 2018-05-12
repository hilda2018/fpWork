package com.fresh.model;

import com.nova.frame.annotation.Id;
import com.nova.frame.annotation.Table;
/**
 * 
 * @ClassName: UsersBelong 
 * @Description: 用户往来单位所属类型
 * @author 杨旭
 * @date 2016-12-15
 *
 */
@Table("usersbelong")
public class UsersBelong {

	@Id(auto = false)
	private String belongid;
	private String userid;
	private String bmodule;
	private String companytype;
	
	public String getBelongid() {
		return belongid;
	}
	public void setBelongid(String belongid) {
		this.belongid = belongid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getBmodule() {
		return bmodule;
	}
	public void setBmodule(String bmodule) {
		this.bmodule = bmodule;
	}
	public String getCompanytype() {
		return companytype;
	}
	public void setCompanytype(String companytype) {
		this.companytype = companytype;
	}
	
	
}
