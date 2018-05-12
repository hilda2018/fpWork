package com.fresh.model;

import com.nova.frame.annotation.Id 

;
import com.nova.frame.annotation.Table;
/**
 * 
 * @ClassName: Dusers 
 * @Description: 用户往来单位关联实体类
 * @author yc
 * @date 2015-9-8 上午11:57:36 
 *
 */
@Table("dusers")
public class DUserss {

	@Id(auto = false)
	private String id;
	private String duserid;
	private String usersid;
	private String isuse;
	
	public String getIsuse() {
		return isuse;
	}
	public void setIsuse(String isuse) {
		this.isuse = isuse;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id 

 = id;
	}
	public String getDuserid() {
		return duserid;
	}
	public void setDuserid(String duserid) {
		this.duserid = duserid;
	}
	public String getUsersid() {
		return usersid;
	}
	public void setUsersid(String usersid) {
		this.usersid = usersid;
	}
	
}
