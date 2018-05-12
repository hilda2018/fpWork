package com.novaone.model;

import java.io.Serializable;

import com.nova.frame.annotation.Id;
import com.nova.frame.annotation.Table;
import com.nova.frame.annotation.Transient;
/**
 * 
 * 
 * @类编号:
 * @类名称:CustomsInfo
 * @内容摘要:常用联系人信息表实体类
 * @author:陈自军
 * @创建日期:2016年8月11日 下午2:05:47
 * @修改人:
 * @修改日期:
 * @修改描述:简单描述修改的内容
 * @version 1.0.0
 *
 */
@SuppressWarnings("serial")
@Table("customsinfo")
public class CustomsInfo implements Serializable{
	
    @Id(auto=false)
    private String customsinfoid;//主键
    private String userid;//外键
    private String username;//姓名
    private String company;//公司名称
    private String email;//EMAIL
    private String tel;//电话号码
    private String currentdate;
    private String initial;//名字首字母
    private String type;//0:市场行情
    private String realname;//真实姓名
    @Transient
    private String id;//转发人在d_user表中的用户id
    //modify 2016-12-08
    private String rootuserid;//顶级父节点 parentid='0'
    private String indate;//填写日期
    private String modifyuser;//最后修改人
    private String modifytime;//最后修改时间
    
	public String getCustomsinfoid() {
		return customsinfoid;
	}
	public void setCustomsinfoid(String customsinfoid) {
		this.customsinfoid = customsinfoid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getCurrentdate() {
		return currentdate;
	}
	public void setCurrentdate(String currentdate) {
		this.currentdate = currentdate;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getInitial() {
		return initial;
	}
	public void setInitial(String initial) {
		this.initial = initial;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * realname
	 *
	 * @return  the realname
	 * @since   1.0.0
	 */
	
	public String getRealname() {
		return realname;
	}
	/**
	 * @param realname the realname to set
	 */
	public void setRealname(String realname) {
		this.realname = realname;
	}
	/**
	 * rootuserid
	 *
	 * @return  the rootuserid
	 * @since   1.0.0
	 */
	
	public String getRootuserid() {
		return rootuserid;
	}
	/**
	 * @param rootuserid the rootuserid to set
	 */
	public void setRootuserid(String rootuserid) {
		this.rootuserid = rootuserid;
	}
	/**
	 * indate
	 *
	 * @return  the indate
	 * @since   1.0.0
	 */
	
	public String getIndate() {
		return indate;
	}
	/**
	 * @param indate the indate to set
	 */
	public void setIndate(String indate) {
		this.indate = indate;
	}
	/**
	 * modifyuser
	 *
	 * @return  the modifyuser
	 * @since   1.0.0
	 */
	
	public String getModifyuser() {
		return modifyuser;
	}
	/**
	 * @param modifyuser the modifyuser to set
	 */
	public void setModifyuser(String modifyuser) {
		this.modifyuser = modifyuser;
	}
	/**
	 * modifytime
	 *
	 * @return  the modifytime
	 * @since   1.0.0
	 */
	
	public String getModifytime() {
		return modifytime;
	}
	/**
	 * @param modifytime the modifytime to set
	 */
	public void setModifytime(String modifytime) {
		this.modifytime = modifytime;
	}
}
