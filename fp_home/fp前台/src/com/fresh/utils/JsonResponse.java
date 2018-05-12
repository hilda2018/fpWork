package com.fresh.utils;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

public class JsonResponse {
	

	public static void writeMsg(Json json,HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		JSONObject jsonObject = new JSONObject();
		if(json.isSuccess()) {
			jsonObject.put("msg", json.getSuccessContent());
		} else {
			jsonObject.put("msg", json.getErrorContent());
		}
		try {
			response.getWriter().write(jsonObject.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void writeJson(Json json,HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("success", json.isSuccess());
		if(json.isSuccess()) {
			jsonObject.put("msg", json.getSuccessContent());
		} else {
			jsonObject.put("msg", json.getErrorContent());
		}
		try {
			response.getWriter().write(jsonObject.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void write(boolean success, String msg,
			HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("success", success);
		jsonObject.put("msg", msg);
		try {
			response.getWriter().write(jsonObject.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void write(String content,HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().write(content);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
