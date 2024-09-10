package com.example.demo.exception;

public class TaskListIsEmptyException extends Exception{
	public TaskListIsEmptyException(String message) {
		super(message);
	}
}
