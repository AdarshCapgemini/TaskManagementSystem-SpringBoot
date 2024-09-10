package com.example.demo.exception;

public class UserRoleAlreadyExistsException extends Exception{
	
	public UserRoleAlreadyExistsException(String message)
	{
		super(message);
	}
}
