package com.example.demo.exception;

public class UserRoleDoesNotExistException extends Exception{
	
	public UserRoleDoesNotExistException(String message)
	{
		super(message);
	}
}
