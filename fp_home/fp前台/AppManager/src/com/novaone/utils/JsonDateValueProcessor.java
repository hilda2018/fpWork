package com.novaone.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
/**
 * 
 * @ClassName: JsonDateValueProcessor 
 * @Description: JSON 日期格式化
 * @author zhaojiyan
 * @date 2015-8-17 上午10:40:48 
 *
 */
public class JsonDateValueProcessor implements JsonValueProcessor {
	private String format ="yyyy-MM-dd";
	
	public JsonDateValueProcessor() {
		super();
	}
	
	public JsonDateValueProcessor(String format) {
		super();
		this.format = format;
	}

	@Override
	public Object processArrayValue(Object paramObject,
			JsonConfig paramJsonConfig) {
		return process(paramObject);
	}

	@Override
	public Object processObjectValue(String paramString, Object paramObject,
			JsonConfig paramJsonConfig) {
		return process(paramObject);
	}
	
	
	private Object process(Object value){
        if(value instanceof Date){  
            SimpleDateFormat sdf = new SimpleDateFormat(format,Locale.CHINA);  
            return sdf.format(value);
        }  
        return value == null ? "" : value.toString();  
    }

}
