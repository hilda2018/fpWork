package com.novaone.model;

import java.io.Serializable;

import com.nova.frame.annotation.Id;
import com.nova.frame.annotation.Table;
@Table("appversion")
public class Version implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id(auto=false)
	private String fid;//主键
	private String version;//版本号
	private String renew;//是否强制更新 0:否 1:是
	private String content;//更新内容
	private String platform;//平台
	
	public Version() {
	}
	public Version(String fid, String version, String renew, String content) {
		super();
		this.fid = fid;
		this.version = version;
		this.renew = renew;
		this.content = content;
	}
	/**
	 * fid
	 *
	 * @return  the fid
	 * @since   1.0.0
	 */
	
	public String getFid() {
		return fid;
	}
	/**
	 * @param fid the fid to set
	 */
	public void setFid(String fid) {
		this.fid = fid;
	}
	/**
	 * version
	 *
	 * @return  the version
	 * @since   1.0.0
	 */
	
	public String getVersion() {
		return version;
	}
	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}
	/**
	 * renew
	 *
	 * @return  the renew
	 * @since   1.0.0
	 */
	
	public String getRenew() {
		return renew;
	}
	/**
	 * @param renew the renew to set
	 */
	public void setRenew(String renew) {
		this.renew = renew;
	}
	/**
	 * content
	 *
	 * @return  the content
	 * @since   1.0.0
	 */
	
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * platform
	 *
	 * @return  the platform
	 * @since   1.0.0
	 */
	
	public String getPlatform() {
		return platform;
	}
	/**
	 * @param platform the platform to set
	 */
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	
	
}
