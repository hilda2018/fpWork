package com.fresh.utils;

public class Json {
	
	private boolean success;
	
	private String successContent;
	
	private String errorContent;
	
	public Json(boolean success, String successContent, String errorContent) {
		this.success = success;
		this.successContent = successContent;
		this.errorContent = errorContent;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getSuccessContent() {
		return successContent;
	}

	public void setSuccessContent(String successContent) {
		this.successContent = successContent;
	}

	public String getErrorContent() {
		return errorContent;
	}

	public void setErrorContent(String errorContent) {
		this.errorContent = errorContent;
	}

}
