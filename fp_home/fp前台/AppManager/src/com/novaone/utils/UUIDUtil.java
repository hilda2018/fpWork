package com.novaone.utils;

import java.lang.reflect.Method;
import java.util.List;
import java.util.UUID;

public class UUIDUtil {

	public static List addUUID(List<?> list, String column) {
		try {
			if(list==null||list.size()==0) {
				return list;
			}
			String first = column.substring(0, 1).toUpperCase();
			String last = column.substring(1, column.length());
			Class<? extends Object> c = list.get(0).getClass();
			for(Object object : list) {
				c = object.getClass(); 
				Method m = c.getDeclaredMethod("set"+first+last, String.class);
				m.invoke(object,UUID.randomUUID().toString());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static Object addUUID(Object object, Class c, String column) {
		try {
			String first = column.substring(0, 1).toUpperCase();
			String last = column.substring(1, column.length());
			c = object.getClass(); 
			Method m = c.getDeclaredMethod("set"+first+last, String.class);
			m.invoke(object,UUID.randomUUID().toString());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return object;
	}
	
	public static String getOrderNo() {
		int r1=(int)(Math.random()*(10));//产生2个0-9的随机数
		int r2=(int)(Math.random()*(10));
		long now = System.currentTimeMillis();//一个13位的时间戳
		String paymentID =String.valueOf(r1)+String.valueOf(r2)+String.valueOf(now);// 订单ID
		return paymentID;
	}
	public static String getUUID(){
		UUID uuid=UUID.randomUUID();
		return uuid.toString();
	}
}
