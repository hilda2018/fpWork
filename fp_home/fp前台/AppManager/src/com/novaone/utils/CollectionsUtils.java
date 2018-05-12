package com.novaone.utils;

import java.util.Collection;

public class CollectionsUtils {
	
	public static boolean isEmpty(Collection<? extends Object> c) {
		return c == null || c.size() == 0;
	}
}
