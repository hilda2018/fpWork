package com.novaone.common;
/**
 * 
 * @ClassName: CommonInfo 
 * @Description: 静态常量类
 * @author zhaojiyan
 * @date 2015-8-13 下午10:15:21 
 *
 */
public class CommonInfo {

	public static final String SUPPLIER_STATUS_NEW = "0";//状态 新制
	public static final String SUPPLIER_STATUS_AGOING = "1";//状态 进行中
	public static final String SUPPLIER_STATUS_COMPLETED = "2";//状态 已完成
	public static final int SUPPLIER_TRANSPORTATION_SHIP = 0;//运输方式 海运
	public static final int SUPPLIER_TRANSPORTATION_AIR = 1;//运输方式 空运
	public static final int CONTRACTCONTAINER_STATUS_NO = 0;//未调整
	public static final int CONTRACTCONTAINER_STATUS_YES = 1;//已调整
	public static final int CONTRACTCONTAINER_STATUS_CONFIRM = 0;//已确认
	public static final int CONTRACTCONTAINER_SUBMIT_NO = 0;//未提交
	public static final int CONTRACTCONTAINER_SUBMIT_YES = 1;//已提交
	public static final int PURCHASESELL_IFQUALITYPROBLEM_YES = 0;//质量无问题
	public static final int PURCHASESELL_IFQUALITYPROBLEM_NO = 1;//质量有问题
	public static final int PURCHASESELL_INSPECTIONMETHOD_SELL = 0;//验货方式--采购商自己
	public static final int PURCHASESELL_INSPECTIONMETHOD_OTHER = 1;//验货方式--第三方验货
	public static final int PURCHASESELL_IFSTORAGEADJUST_NO = 0;//没有入库调整
	public static final int PURCHASESELL_IFSTORAGEADJUST_YES = 1;//已入库调整
	public static final int STORAGESTATE_NO = 0;//未入库
	public static final int STORAGESTATE_YES = 1;//已入库
	public static final int STORAGESTATE_UPD = 2;//入库调整中
	public static final int STORAGESTATE_CONFIRM = 3;//入库已确认
	/**
	 * 0代表关闭 1代表开启
	 */
	public static final String SIGNLE_LOGIN_SWITCH="0";
	/**
	 * 单点登录退出地址
	 */
	public static final String SIGNLE_URL="http://www.freshport.com:8080/logout?service=http://www.freshport.com";
	/**
	 * 单点登录登录地址
	 */
	public static final String SIGNLE_LOGIN_URL="http://www.freshport.com:8080/login?service=";
	/**
	 * 是否需要单点登录验证 1代表需要
	 */
	public static final String SIGNLE_NEED_FLAG="1";
	/**
	 * 
	 */
	public static final String PROJECT_SERVER_URL = "http://www.freshport.com";
	
	
}
