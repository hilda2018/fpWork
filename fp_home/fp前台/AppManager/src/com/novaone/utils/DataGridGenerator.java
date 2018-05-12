package com.novaone.utils;


import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class DataGridGenerator<T> {
	private JSONArray jsonArray = new JSONArray();
	private JSONObject resultJson = new JSONObject();
	public String create(List<T> data,long total,String[] excludes,ExtensibleJsonProcessor extensibleJsonProcess) {
		
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(excludes);
		for(T t : data) {
			JSONObject jsonObject = JSONObject.fromObject(t,jsonConfig);
			extensibleJsonProcess.extraProcess(t,jsonObject);
			jsonArray.add(jsonObject);
		}
		resultJson.put("total", total);
		resultJson.put("rows", jsonArray);
		return resultJson.toString();
	}
	
	public String create(List<T> data,long total,String[] excludes) {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(excludes);
		for(T t : data) {
			JSONObject jsonObject = JSONObject.fromObject(t,jsonConfig);
			jsonArray.add(jsonObject);
		}
		resultJson.put("total", total);
		resultJson.put("rows", jsonArray);
		return resultJson.toString();
	}
	
	public String create(List<T> data,long total) {
		prepare(data, total);
		return resultJson.toString();
	}
	
	public void prepare (List<T> data,long total) {
		for(T t : data) {
			JSONObject jsonObject = JSONObject.fromObject(t);
			jsonArray.add(jsonObject);
		}
		resultJson.put("total", total);
		resultJson.put("rows", jsonArray);
	}
	
	public void prepare (List<T> data,long total,ExtensibleJsonProcessor extensibleJsonProcessor) {
		for(T t : data) {
			JSONObject jsonObject = JSONObject.fromObject(t);
			extensibleJsonProcessor.extraProcess(t, jsonObject);
			jsonArray.add(jsonObject);
		}
		resultJson.put("total", total);
		resultJson.put("rows", jsonArray);
	}
	
	public String create(List<T> data,long total,DataGridBuilder dataGridBuilder) {
		prepare(data, total,dataGridBuilder);
		JSONArray footeArray = new JSONArray();
		dataGridBuilder.createFooter(footeArray);
		resultJson.put("footer",footeArray);
		return resultJson.toString();
	}
	
	public String create(List<T> data,ExtensibleJsonProcessor extensibleJsonProcesss) {
		for(T t : data) {
			JSONObject jsonObject = JSONObject.fromObject(t);
			extensibleJsonProcesss.extraProcess(t,jsonObject);
			jsonArray.add(jsonObject);
		}
		return jsonArray.toString();
	}

	public JSONObject getResultJson() {
		return resultJson;
	}
}
