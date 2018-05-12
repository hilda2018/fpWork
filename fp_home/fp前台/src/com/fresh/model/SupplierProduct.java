package com.fresh.model;

import java.io.Serializable;

import com.nova.frame.annotation.Id;
import com.nova.frame.annotation.Table;

@Table("supplierproduct")
public class SupplierProduct implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 供应商产品ID
	 */
	@Id(auto = false)
	private String _SupplierProductID;
	
	public String getsupplierproductid() {
		return _SupplierProductID;
	}

	public void setsupplierproductid(String _SupplierProductID) {
		this._SupplierProductID = _SupplierProductID;
	}

	
	/**
	 * 用户ID
	 */
	private String _UserID;
	
	public String getuserid() {
		return _UserID;
	}

	public void setuserid(String _UserID) {
		this._UserID = _UserID;
	}
	
	/**
	 * 产品
	 */
	private String _ProductID;
	
	public String getproductid() {
		return _ProductID;
	}

	public void setproductid(String _ProductID) {
		this._ProductID = _ProductID;
	}
	
	/**
	 * 品种
	 */
	private String _VarietiesID;
	
	public String getvarietiesid() {
		return _VarietiesID;
	}

	public void setvarietiesid(String _VarietiesID) {
		this._VarietiesID = _VarietiesID;
	}
	
	/**
	 * 品牌
	 */
	private String _SupplierLabel;
	
	public String getsupplierlabel() {
		return _SupplierLabel;
	}

	public void setsupplierlabel(String _SupplierLabel) {
		this._SupplierLabel = _SupplierLabel;
	}

	/**
	 * 包装规格
	 */
	private String _PackageID;
	
	public String getpackageid() {
		return _PackageID;
	}

	public void setpackageid(String _PackageID) {
		this._PackageID = _PackageID;
	}
	
	/**
	 * 起订量
	 */
	private String _MOQ;
	
	public String getmoq() {
		return _MOQ;
	}

	public void setmoq(String _MOQ) {
		this._MOQ = _MOQ;
	}
	
	/**
	 * 起订量单位
	 */
	private String _MOQUnit;
	
	public String getmoqunit() {
		return _MOQUnit;
	}

	public void setmoqunit(String _MOQUnit) {
		this._MOQUnit = _MOQUnit;
	}
	
	/**
	 * 产地(国家/地区)
	 */
	private String _CountryID;
	
	public String getcountryid() {
		return _CountryID;
	}

	public void setcountryid(String _CountryID) {
		this._CountryID = _CountryID;
	}
	
	/**
	 * 国家区域
	 */
	private String _CountryAreaID;
	
	public String getcountryareaid() {
		return _CountryAreaID;
	}

	public void setcountryareaid(String _CountryAreaID) {
		this._CountryAreaID = _CountryAreaID;
	}
	
	/**
	 * 年供应量
	 */
	private String _YearSupply;
	
	public String getyearsupply() {
		return _YearSupply;
	}

	public void setyearsupply(String _YearSupply) {
		this._YearSupply = _YearSupply;
	}
	
	/**
	 * 年供应量单位
	 */
	private String _YearSupplyUnit;
	
	public String getyearsupplyunit() {
		return _YearSupplyUnit;
	}

	public void setyearsupplyunit(String _YearSupplyUnit) {
		this._YearSupplyUnit = _YearSupplyUnit;
	}
	
	/**
	 * 果园名称
	 */
	private String _Orchard;
	
	public String getorchard() {
		return _Orchard;
	}

	public void setorchard(String _Orchard) {
		this._Orchard = _Orchard;
	}
	
	/**
	 * 种植面积
	 */
	private String _PlantArea;
	
	public String getplantarea() {
		return _PlantArea;
	}

	public void setplantarea(String _PlantArea) {
		this._PlantArea = _PlantArea;
	}
	
	/**
	 * 种植面积单位
	 */
	private String _PlantAreaUnit;
	
	public String getplantareaunit() {
		return _PlantAreaUnit;
	}

	public void setplantareaunit(String _PlantAreaUnit) {
		this._PlantAreaUnit = _PlantAreaUnit;
	}
	
	/**
	 * 起始供应时间月
	 */
	private String _StartSupplyMonth;
	
	public String getstartsupplymonth() {
		return _StartSupplyMonth;
	}

	public void setstartsupplymonth(String _StartSupplyMonth) {
		this._StartSupplyMonth = _StartSupplyMonth;
	}
	
	/**
	 * 起始供应时间旬
	 */
	private String _StartSupplyTen;
	
	public String getstartsupplyten() {
		return _StartSupplyTen;
	}

	public void setstartsupplyten(String _StartSupplyTen) {
		this._StartSupplyTen = _StartSupplyTen;
	}
	
	/**
	 * 结束供应时间月
	 */
	private String _EndSupplyMonth;
	
	public String getendsupplymonth() {
		return _EndSupplyMonth;
	}

	public void setendsupplymonth(String _EndSupplyMonth) {
		this._EndSupplyMonth = _EndSupplyMonth;
	}
	
	/**
	 * 结束供应时间旬
	 */
	private String _EndSupplyTen;
	
	public String getendsupplyten() {
		return _EndSupplyTen;
	}

	public void setendsupplyten(String _EndSupplyTen) {
		this._EndSupplyTen = _EndSupplyTen;
	}
	
	/**
	 * 运输方式
	 */
	private String _Transport;
	
	public String gettransport() {
		return _Transport;
	}

	public void settransport(String _Transport) {
		this._Transport = _Transport;
	}
	
	/**
	 * 详细介绍
	 */
	private String _Introduction;
	
	public String getintroduction() {
		return _Introduction;
	}

	public void setintroduction(String _Introduction) {
		this._Introduction = _Introduction;
	}
	
	
	/**
	 * 供应商产品展示
	 */
	private String _SupplierProductImg;
	
	public String getsupplierproductimg() {
		return _SupplierProductImg;
	}

	public void setsupplierproductimg(String _SupplierProductImg) {
		this._SupplierProductImg = _SupplierProductImg;
	}
	
	
	/**
	 * 供应商产品图片视频ID
	 */
	private String _PicVideoID;
	
	public String getpicvideoid() {
		return _PicVideoID;
	}

	public void setpicvideoid(String _PicVideoID) {
		this._PicVideoID = _PicVideoID;
	}
	
	/**
	 * 图片视频地址
	 */
	private String _PicVideoPath;
	
	public String getpicvideopath() {
		return _PicVideoPath;
	}

	public void setpicvideopath(String _PicVideoPath) {
		this._PicVideoPath = _PicVideoPath;
	}
	
	/**
	 * 图片视频简介
	 */
	private String _PicVideoNotes;
	
	public String getpicvideonotes() {
		return _PicVideoNotes;
	}

	public void setpicvideonotes(String _PicVideoNotes) {
		this._PicVideoNotes = _PicVideoNotes;
	}
	
	/**
	 * 文件类型
	 */
	private String _FileType;
	
	public String getfileType() {
		return _FileType;
	}

	public void setfileType(String _FileType) {
		this._FileType = _FileType;
	}
	
	/**
	 * 所属内容类型
	 */
	private String _ContentType;
	
	public String getcontenttype() {
		return _ContentType;
	}

	public void setcontenttype(String _ContentType) {
		this._ContentType = _ContentType;
	}
}
