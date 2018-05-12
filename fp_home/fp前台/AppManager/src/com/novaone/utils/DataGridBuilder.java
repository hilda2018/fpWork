package com.novaone.utils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public abstract class DataGridBuilder implements ExtensibleJsonProcessor{
	
	@Override
	public void extraProcess(Object currentObject, JSONObject currentJsonObject) {
		
	}
	
	public abstract void createFooter(JSONArray footeArray);
}
