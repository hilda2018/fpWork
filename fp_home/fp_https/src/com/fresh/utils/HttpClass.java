package com.fresh.utils;

import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.jdom.Document;
import org.jdom.input.SAXBuilder;
import org.xml.sax.InputSource;

import com.nova.frame.logging.Log;
import com.nova.frame.logging.Logs;

public class HttpClass {
	private static final Log logger = Logs.get();
	/**
	 * 发送命令
	 * 
	 * @param msg
	 *            URL参数信息
	 * @return XML描述
	 */
	public static Document sendMsg(String msg) {
		Document doc = null;
		try {
			String data = msg;
			// Send data
			URL url = new URL("http://vip.mston.com/SMSAPI/SMSSV.aspx?");
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
			wr.write(data);
			wr.flush();
			wr.close();

			// Get the response
			InputSource source = new InputSource(conn.getInputStream());
			// 创建一个新的SAXBuilder
			SAXBuilder sb = new SAXBuilder();
			try {
				doc = sb.build(source);
			} catch (Exception e) {
				logger.error("发送短信异常："+e.getMessage());
			}
		} catch (Exception e) {
			logger.error("发送短信异常："+e.getMessage());
		}
		return doc;
	}

	/**
	 * 发送短信
	 * 
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @param mobiles
	 *            终端号码，多个号码时使用半角逗号分隔，每次最多提交 300 个号码
	 * @param msg
	 *            信息内容
	 * @param ExNo
	 *            扩展号码，可为空，为空时不做扩展
	 * @param AtTime
	 *            定时时间，可为空 , 为空时立即发送 （小于当前时间的将被处理为立即发送 ，超过当前时间 365 天的 将返回失败）
	 * @param ExParam
	 *            可为空
	 * @return XML描述
	 */
	public static Document send(String username, String password,
			String mobiles, String msg, String ExNo, String AtTime,
			String ExParam) {
		StringBuffer sb = new StringBuffer();
		sb.append("Command=SendSMS");
		sb.append("&Username=" + username);
		sb.append("&Password=" + password);
		sb.append("&Mobiles=" + mobiles);
		String msg_temp = "";
		try {
			msg_temp = URLEncoder.encode(msg, "gb2312");
		} catch (UnsupportedEncodingException e) {
			logger.error("发送短信异常："+e.getMessage());
		}
		sb.append("&Msg=" + msg_temp);
		sb.append("&ExNo=" + ExNo);
		sb.append("&AtTime=" + AtTime);
		sb.append("&ExParam=" + ExParam);
		return HttpClass.sendMsg(sb.toString());
	}

	/**
	 * 接收短信
	 * 
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @param FromID
	 *            接收位置（参数为空时 从第一个未阅读信息接收 参数为“ 0 ”时 从系统保存的第一个信息开始接收）
	 * @param ExParam
	 *            扩展参数,可为空
	 * @return
	 */
	public static Document recv(String username, String password,
			String FromID, String ExParam) {
		StringBuffer sb = new StringBuffer();
		sb.append("Command=RecvSMS");
		sb.append("&Username=" + username);
		sb.append("&Password=" + password);
		sb.append("&FromID=" + FromID);
		sb.append("&ExParam=" + ExParam);
		return HttpClass.sendMsg(sb.toString());
	}

	/**
	 * 设置属性
	 * 
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @param propertyName
	 *            属性名称
	 * @param propertyValue
	 *            属性值
	 * @return XML描述
	 */
	public static Document setProperty(String username, String password,
			String propertyName, String propertyValue) {
		StringBuffer sb = new StringBuffer();
		sb.append("Command=SetProperty");
		sb.append("&Username=" + username);
		sb.append("&Password=" + password);
		sb.append("&PropertyName=" + propertyName);
		sb.append("&PropertyValue=" + propertyValue);
		return HttpClass.sendMsg(sb.toString());
	}

	/**
	 * 获得属性
	 * 
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @return XML描述
	 */
	public static Document getProperty(String username, String password) {
		StringBuffer sb = new StringBuffer();
		sb.append("Command=GetProperty");
		sb.append("&Username=" + username);
		sb.append("&Password=" + password);
		return HttpClass.sendMsg(sb.toString());
	}

	/**
	 * 扩展命令
	 * 
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @return XML描述
	 */
	public static Document ExCommand(String username, String password,
			String param) {
		StringBuffer sb = new StringBuffer();
		sb.append("Command=ExCommand");
		sb.append("&Username=" + username);
		sb.append("&Password=" + password);
		sb.append("&Param=" + param);
		return HttpClass.sendMsg(sb.toString());
	}
}
