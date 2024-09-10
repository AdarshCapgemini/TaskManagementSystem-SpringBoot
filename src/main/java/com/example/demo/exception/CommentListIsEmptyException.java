package com.example.demo.exception;

public class CommentListIsEmptyException extends Exception{
	
	private String message;
	
	public CommentListIsEmptyException(String message) {
		this.message=message;
	}
	
	public String getMessage() {
		return this.message;
	}

}
