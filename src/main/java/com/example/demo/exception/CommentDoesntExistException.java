package com.example.demo.exception;

public class CommentDoesntExistException extends Exception{
	
	String message;
	
	public CommentDoesntExistException(String message) {
		this.message=message;
	}
	
	public String getMessage() {
		return this.message;
	}
	

}
