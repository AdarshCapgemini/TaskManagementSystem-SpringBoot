package com.example.demo.exception;

public class ProjectListIsEmptyException extends Exception {
private String message;
	
	public ProjectListIsEmptyException(String message) {this.message=message;}
	
	public String getMessage() {
		return this.message;
		}

}
