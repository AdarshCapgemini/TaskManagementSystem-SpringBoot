package com.example.demo.exception;

public class NotificationDoesntExistException extends Exception{
	
	private String message;
	
	public NotificationDoesntExistException(String message) {
		this.message=message;
	}
	
	public String getMessage() {
		return this.message;
	}

}
