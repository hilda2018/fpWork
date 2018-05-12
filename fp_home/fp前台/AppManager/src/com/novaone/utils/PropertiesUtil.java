package com.novaone.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * 
 *  [ 项目名 ]  : 主数据管理
 *  [ 公司名 ]  : 济南易贸创想软件有限公司
 *	[ 模块名 ]  : 遍历properties文件
 *	[ 文件名 ]  : 
 *	[ 功 能 ]  : 
 *	[ 作 者 ]  : chenzijun
 *	[ 版 本 ]  : 1.0
 *  [ 时 间 ]  : 2016年3月1日 上午10:02:15
 */
public class PropertiesUtil {
	public static Map getFileIO(String fileName){
		Properties  prop =new Properties();
		Map propMap=new HashMap();
		InputStream in=PropertiesUtil.class.getResourceAsStream(fileName);
		try {
			prop.load(in);
			Set keyset=prop.keySet();
			for(Object object:keyset){
				String propValue = prop.getProperty(object.toString()).toString();
				propMap.put(object.toString(), propValue);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return propMap;
	}
	/**
	 * 根据key值获取value
	 * @方法名称:getValue
	 * @内容摘要: ＜列出主要功能、调用说明、异常说明、业务逻辑等＞
	 * @param fileNamePath "src/system.properties"
	 * @param key
	 * @return
	 * @throws IOException 
	 * String
	 * @exception 
	 * @author:chenzijun
	 * @创建日期:2016年10月26日-下午3:49:36
	 */
	 public static String getValue(String fileNamePath, String key)throws IOException {  
	        Properties props = new Properties();  
	        InputStream in = null;  
	        try {  
	            in = new FileInputStream(fileNamePath);  
	            // 如果将in改为下面的方法，必须要将.Properties文件和此class类文件放在同一个包中  
	            //in = propertiesTools.class.getResourceAsStream(fileNamePath);  
	            props.load(in);  
	            String value = props.getProperty(key);  
	            // 有乱码时要进行重新编码  
	            // new String(props.getProperty("name").getBytes("ISO-8859-1"), "GBK");  
	            return value;  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	            return null;  
	        } finally {  
	            if (null != in)  
	                in.close();  
	        }  
	    }  
	 public static void main(String[] args) {
		try {
			System.out.println(PropertiesUtil.getValue("system.properties", "file_uploadpath"));;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
