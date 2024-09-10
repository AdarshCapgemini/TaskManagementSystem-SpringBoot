package com.example.demo.exception;

public class TaskDoesntExistException extends Exception {
	public TaskDoesntExistException(String message) {
		super(message);
	}
}
