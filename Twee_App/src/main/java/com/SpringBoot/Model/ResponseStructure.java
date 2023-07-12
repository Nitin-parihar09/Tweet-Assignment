package com.SpringBoot.Model;

import java.util.List;

public class ResponseStructure <T> {
	
	private int statusCode;
	private String message;
	private T data;
	
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getData() {
		return data;
	}
	@SuppressWarnings("unchecked")
	public void setData(List<Object> executeQuery) {
		this.data = (T) executeQuery;
	}
}
