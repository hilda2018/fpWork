/**
 * 
 */
package com.novaone.model;

import java.io.Serializable;

import com.nova.frame.annotation.Id;
import com.nova.frame.annotation.Table;

/**
 * 
 * APP操作记录表实体类
 * @类编号:
 * @类名称:Oplog
 * @内容摘要: //说明主要功能。
 * @author:陈自军
 * @创建日期:2016年8月11日 下午5:10:57
 * @修改人:
 * @修改日期:
 * @修改描述:简单描述修改的内容
 * @version 1.0.0
 *
 */
@SuppressWarnings("serial")
@Table("oplog")
public class Oplog implements Serializable {
	@Id(auto = false)
	private String oplogid;//操作日志ID
	private String userid;//用户名
	private String opdate;//操作时间
	private String opfield;//操作字段
	private Integer system;//所属业务模块
	
	public Oplog() {
	}

	public Oplog(String oplogid, String userid, String opdata, String opfield,
			Integer system) {
		super();
		this.oplogid = oplogid;
		this.userid = userid;
		this.opdate = opdata;
		this.opfield = opfield;
		this.system = system;
	}
	/**
	 * 
	 * @param opfield //操作字段
	 * @param system//所属业务模块
	 */
	public Oplog(String userid,String opfield, Integer system){
		this.userid = userid;
		this.opfield=opfield;
		this.system=system;
	}
	
	public String getOplogid() {
		return oplogid;
	}

	public void setOplogid(String oplogid) {
		this.oplogid = oplogid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getOpfield() {
		return opfield;
	}

	public void setOpfield(String opfield) {
		this.opfield = opfield;
	}

	public Integer getSystem() {
		return system;
	}

	public void setSystem(Integer system) {
		this.system = system;
	}

	/**
	 * opdate
	 *
	 * @return  the opdate
	 * @since   1.0.0
	 */
	
	public String getOpdate() {
		return opdate;
	}

	/**
	 * @param opdate the opdate to set
	 */
	public void setOpdate(String opdate) {
		this.opdate = opdate;
	}
	

}
