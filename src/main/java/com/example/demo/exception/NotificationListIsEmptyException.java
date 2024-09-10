package com.example.demo.exception;


public class NotificationListIsEmptyException extends Exception{
	
	private String message;
	
	public NotificationListIsEmptyException(String message) {
		this.message=message;
	}
	
	public String getMessage() {
		return this.message;
	}

}
