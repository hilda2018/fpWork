package com.novaone.constants;

/**
 * 
 * 市场行情常量类
 * @类编号:
 * @类名称:Constants
 * @内容摘要: //说明主要功能。
 * @author:陈自军
 * @创建日期:2016年8月11日 下午5:18:57
 * @修改人:
 * @修改日期:
 * @修改描述:简单描述修改的内容
 * @version 1.0.0
 *
 */
public class Constants {
	/**非转发,转发没有确认*/
	 public static final Integer FLAG_ISTRANSMIT_0=0;
	 /**转发,转发确认*/
	 public static final Integer FLAG_ISTRANSMIT_1=1;
	 /**
	  * 运输方式:海运
	  */
	 public static final Integer FLAG_TRANSPORT_0=0;
	 /**
	  * 运输方式:空运
	  */
	 public static final Integer FLAG_TRANSPORT_1=1;
	 /**
	  * 运输方式:陆运
	  */
	 public static final Integer FLAG_TRANSPORT_2=2;
	 /**
	  * 市场类型:辉展市场
	  */
	 public static final Integer FLAG_MARKETTYPE_0=0;
	 /**
	  * 市场类型:江南市场
	  */
	 public static final Integer FLAG_MARKETTYPE_1=1;
	 /**
	  * 品项类型:国家
	  */
	 public static final Integer FLAG_TYPE_0=0;
	 /**
	  * 品项类型:品名
	  */
	 public static final Integer FLAG_TYPE_1=1;
	 /**
	  * 品项类型:品种
	  */
	 public static final Integer FLAG_TYPE_2=2;
	 /**
	  * 品项类型:规格
	  */
	 public static final Integer FLAG_TYPE_3=3;
	 /**
	  * 品项类型:销售商
	  */
	 public static final Integer FLAG_TYPE_4=4;
	 /**
	  * 品项类型:供应商
	  */
	 public static final Integer FLAG_TYPE_5=5;
	 /**
	  * 品项类型:品牌
	  */
	 public static final Integer FLAG_TYPE_6=6;
	 
	 /**
	  * 品项类型:重量
	  */
	 public static final Integer FLAG_TYPE_7=7;
	 /**
	  * 品项类型:包装
	  */
	 public static final Integer FLAG_TYPE_8=8;
	 /**
	  * 品项类型:品质
	  */
	 public static final Integer FLAG_TYPE_9=9;
	 /**
	  * 品项类型:销售描述
	  */
	 public static final Integer FLAG_TYPE_10=10;
	 /**
	  * 品项类型:板数
	  */
	 public static final Integer FLAG_TYPE_11=11;
	 /**品项类型:新旧*/
	 public static final Integer FLAG_TYPE_12 = 12;
	 /**
	  * 是: Y
	  */
	 public  static final String FLAG_Y="Y";
	 /**
	  * 否: N
	  */
	 public static final String FLAG_N="N";
	 /**
	  * 构成主账号名称
	  */
	 public static final String JOIN_P = "_P";
	 /**
	  * 用户注册角色id
	  * 相关sql:select * from d_role(价格app用户个人)
	  */
	 public static final String ROLEID = "18b57921-dbf7-4221-9e04-1bc1ecbcfce3";
	 /**
	  * 是否是app用户
	  */
	 public static final String ISAPP = "是";
	 /**
	  * 币别:人民币  
	  * select * from currency;
	  */
	 public static final String CURRENCY_RMB="1";
	 /**
		 * 数据返回成功
		 */
		public static final String RESULT_SUCCESS="数据返回成功!";
		/**
		 * "数据返回失败
		 */
		public static final String RESULT_FAILURE="数据返回失败!";
		/**
		 * 数据更新成功
		 */
		public static final String UPDATE_SUCCESS="数据更新成功!";
		/**
		 * 数据更新失败
		 */
		public static final String UPDATE_FAILURE="数据更新失败!";
		/**
		 * 数据保存成功
		 */
		public static final String SAVE_SUCCESS="数据保存成功!";
		/**
		 * 数据保存失败
		 */
		public static final String SAVE_FAILURE="数据保存失败!";
		/**
		 * 数据删除成功
		 */
		public static final String DELETE_SUCCESS="数据删除成功!";
		/**
		 * 数据删除失败
		 */
		public static final String DELETE_FAILURE="数据删除失败!";
		/**
		 * 常用联系人:市场行情/日志:市场行情
		 */
		public static final String TYPE_0 = "0";
		/**
		 * AppKey
		 */
		public static final String APP_KEY = "45112e556acb5c1b98f68cba";
		/**
		 * Master Secret
		 */
		public static final String APP_CRE = "d1a6ed95d5ed41a2124c0c0d";
		/**
		 * APP转发推送消息
		 */
		public static final String APP_MSG = "推送消息：你收到一份邮件请查收!";
		/**
		 * 账号类型 :主账号
		 */
		public static final String USERTYPE_PRIMARY="PRIMARY";
		/**
		 * 账号类型:子账号
		 */
		public static final String USERTYPE_PERSION="PERSION";
		/**
		 * 市场行情菜单编码:220104  价格查询(个人)
		 */
		public static final String MENU_CODE_PERSON = "220104";
		/**
		 * 市场行情菜单编码:220105  价格查询(公司)
		 */
		public static final String MENU_CODE_COMPANY = "220105";
		/**数据状态 全部:0 */
		public static final String DATASTATE_0 = "0";
		/**数据状态 个人:1 */
		public static final String DATASTATE_1 = "1";
		/**平台类型:android*/
		public static final String OS_ANDROID = "android";
		/**平台类型:ios*/
		public static final String OS_IOS = "ios";
		/**上传文件路径*/
		public static final String FILE_UPLOADPATH="/pictemp/resources/";
	 
	 


}
