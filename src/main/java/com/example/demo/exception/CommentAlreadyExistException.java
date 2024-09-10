package com.example.demo.exception;

public class CommentAlreadyExistException extends Exception{
	
	private String message;
	
	public CommentAlreadyExistException(String message) {
		this.message=message;
	}
	
	public String getMessage() {
		return this.message;
	}
	

}
