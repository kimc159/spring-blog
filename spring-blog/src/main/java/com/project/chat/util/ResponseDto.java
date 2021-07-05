package com.project.chat.util;


public class ResponseDto<T> {
	int status;
	T data;
	
	public ResponseDto() {
		
	}
	public ResponseDto(int status, T data) {
		super();
		this.status = status;
		this.data = data;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
}
