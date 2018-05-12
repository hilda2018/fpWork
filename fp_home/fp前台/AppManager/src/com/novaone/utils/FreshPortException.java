package com.novaone.utils;

import java.util.HashMap;

import com.novaone.common.exception.NovaException;
import com.novaone.common.util.JSONProcessor;

/**
 * 
 * @ClassName: FreshPortException 
 * @Description: 生鲜港自定义异常
 * @author zhaojiyan
 * @date 2015-8-7 上午11:20:13 
 *
 */
public class FreshPortException extends RuntimeException {

	
	private static final long serialVersionUID = 4373622947583523194L;
	
	public static final String errorInfoCode = "001";//参数格式错误
	
	//错误编码
	private String errorCode ="";


	/**
	 * 构造函数
	 * @param errorCode 错误编码
	 * @param errorInfo 错误信息
	 * @param ex
	 */
	public FreshPortException(String errorCode, String errorInfo, Exception ex){
		super(errorInfo, ex);
		this.errorCode = errorCode;
	}
	
	public FreshPortException(String errorCode, String errorInfo) {
		super(errorInfo);
		this.errorCode = errorCode;
	}

	/**
	 * 返回json错误信息数据
	 * @return
	 */
	public HashMap<String,Object> getJsonData(){
		StringBuilder message = new StringBuilder(this.getMessage());

		Throwable tempEx =	this.getCause();
		while(tempEx!=null){
			message.append(tempEx.getMessage());
			tempEx = tempEx.getCause();
		}

		HashMap<String,Object> json = new HashMap<String,Object>();
		json.put("code", this.getErrorCode());
		json.put("message", message.toString());
		return json;
	}

	/**
	 * 返回错误信息数据串
	 * @return
	 */
	public String toJsonString(){
		HashMap<String,Object> json = this.getJsonData();
		String resultStr = JSONProcessor.MapToStr(json);
		return resultStr;
	}
	public String getErrorCode(){
		return this.errorCode;
	}
	
}
