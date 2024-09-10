package com.example.demo.exception;

public class NotificationAlreadyExistException extends Exception{
	
	private String message;
	
	public NotificationAlreadyExistException(String message) {
		this.message=message;
	}
	
	public String getMessage() {
		return this.message;
	}
	

}
	