package com.fresh.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;
import org.jdom.Element;


/**
 * 短信工具
 * @author chengql
 * 
 */
public class MessageUtil {

	public static boolean result;
	private static Log logger = LogFactory.getLog(MessageUtil.class);
	
	/**
	 * 生成验证码信息
	 * @return
	 */
	public static String generateVerifyMsg(){
		return " 手机认证验证码：";
	}
	
	public static void main(String[] args) {
	}
	
	public static boolean sendSMS(String mobileNo,String content){
		Document doc = null;
		doc = HttpClass.send("danielyz", "yzjd3673195",mobileNo,MessageUtil.generateVerifyMsg()+content+"【新 疆 机 电 网】", "", "", "");
		try {
			Element root = doc.getRootElement();
			return (Integer.parseInt(root.getChildText("Result").trim())==0);
		} catch (Exception ex) {
			logger.error("手机验证短信发送失败："+ex.getMessage());
			return false;
		}
	}
	
	public static boolean sendSMS4Inquire(String mobileNo,String content){
		Document doc = null;
		doc = HttpClass.send("danielyz", "yzjd3673195",mobileNo,content, "", "", "");
		try {
			Element root = doc.getRootElement();
			return (Integer.parseInt(root.getChildText("Result").trim())==0);
		} catch (Exception ex) {
			logger.error("询盘提醒短信发送失败："+ex.getMessage());
			return false;
		}
	}
}
