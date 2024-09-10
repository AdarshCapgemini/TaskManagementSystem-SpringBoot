package com.example.demo.dto;

public class SuccessResponseDto {
	
	private String code;
	private String message;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public SuccessResponseDto(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
}