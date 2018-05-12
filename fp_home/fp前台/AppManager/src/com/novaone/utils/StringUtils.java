package com.novaone.utils;

public class StringUtils {
	
	public static boolean isEmpty(Object o) {
		
		return o == null || o.equals("");
	}
	
	public static boolean isNotEmpty(Object o) {
		
		return !isEmpty(o);
	}
}
