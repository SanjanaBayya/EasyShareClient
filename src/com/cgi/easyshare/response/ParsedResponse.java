package com.cgi.easyshare.response;

public class ParsedResponse {
	private String service;
	private String message;
	private String code;
	private String data;
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}

}
