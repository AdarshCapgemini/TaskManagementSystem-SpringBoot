package com.example.demo.exception;

public class ProjectAlreadyExistsException extends Exception {
private String message;
	
	public ProjectAlreadyExistsException(String message) {this.message=message;}
	
	public String getMessage() {
		return this.message;
		}

}
