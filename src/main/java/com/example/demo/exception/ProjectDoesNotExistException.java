package com.example.demo.exception;

public class ProjectDoesNotExistException extends Exception {
private String message;
	
	public ProjectDoesNotExistException(String message) {this.message=message;}
	
	public String getMessage() {
		return this.message;
		}

}
