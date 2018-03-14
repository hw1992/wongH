package com.wong.common;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashMap;

public class JsonResult {
	
	private String resultCode;
	private String message;
	private Object data = new HashMap<String,Object>();
	
	public JsonResult() {
		this.setResultCode(ResultCode.SUCCESS);
		this.setMessage("成功！");
		
	}
	
	public JsonResult(ResultCode resultCode) {
		this.setResultCode(resultCode);
		this.setMessage(resultCode.msg());
	}
	
	public JsonResult(ResultCode resultCode, String message) {
		this.setResultCode(resultCode);
		this.setMessage(message);
	}
	
	public JsonResult(ResultCode resultCode, String message, Object data) {
		this.setResultCode(resultCode);
		this.setMessage(message);
		this.setData(data);
	}
	
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(ResultCode resultCode) {
		this.resultCode = resultCode.val();
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	@JsonIgnore
	public Boolean isSuccess() {
		return resultCode.equals(ResultCode.SUCCESS.val());
	}

	@Override
	public String toString() {
		return "JsonResult{" +
				"resultCode='" + resultCode + '\'' +
				", message='" + message + '\'' +
				", data=" + data +
				'}';
	}
}
