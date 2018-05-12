package com.fresh.utils;

import java.util.Map;

import javax.servlet.ServletContext;

/**
 * 根据名称获得系统常用的参数
 * 
 * @author 于采兴
 * @version 2.0 2015-7-28
 */
public class SysConfigUtils {
	
	@SuppressWarnings("unchecked")
	public static String getConfigValue(ServletContext sc,String key){
		return ((Map<String,String>)sc.getAttribute("sysConfigs")).get(key);
	}
}
