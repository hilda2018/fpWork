/**
 * 
 */
package com.novaone.model;

import java.io.Serializable;

import org.apache.struts2.json.annotations.JSON;

import com.nova.frame.annotation.Id;
import com.nova.frame.annotation.Table;

/**
 * 记录添加 编辑 转发的时候 具有图片上传下载的功能
 * 记录复制的时候图片信息清空
 * @类编号:
 * @类名称:Mqpicture
 * @内容摘要: //说明主要功能。
 * @author:陈自军
 * @创建日期:2016年8月30日 下午2:01:54
 * @修改人:
 * @修改日期:
 * @修改描述:简单描述修改的内容
 * @version 1.0.0
 *
 */

@SuppressWarnings("serial")
@Table("mqpicture")
public class Mqpicture implements Serializable {
	@Id(auto=false)
	private String mqpictureid;//市场图片ID
	private String mquotationid;//市场行情ID
	private String pic;//图片名称
	private String currentdate;
	
	public Mqpicture() {
	}
	public Mqpicture(String mqpictureid, String mquotationid, String pic,
			String currentdate) {
		super();
		this.mqpictureid = mqpictureid;
		this.mquotationid = mquotationid;
		this.pic = pic;
		this.currentdate = currentdate;
	}
	public String getMqpictureid() {
		return mqpictureid;
	}
	public void setMqpictureid(String mqpictureid) {
		this.mqpictureid = mqpictureid;
	}
	public String getMquotationid() {
		return mquotationid;
	}
	public void setMquotationid(String mquotationid) {
		this.mquotationid = mquotationid;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
    @JSON(format = "yyyy-MM-dd HH:mm:ss")
	public String getCurrentdate() {
		return currentdate;
	}
	public void setCurrentdate(String currentdate) {
		this.currentdate = currentdate;
	}
	
	
	

}
