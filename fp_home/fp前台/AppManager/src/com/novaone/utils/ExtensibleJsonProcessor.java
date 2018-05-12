package com.novaone.utils;

import net.sf.json.JSONObject;


public interface ExtensibleJsonProcessor {
	
	public void extraProcess(Object currentObject,JSONObject currentJsonObject);
}
