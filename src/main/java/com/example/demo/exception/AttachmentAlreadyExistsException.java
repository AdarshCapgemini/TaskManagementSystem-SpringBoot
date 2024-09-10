package com.example.demo.exception;

public class AttachmentAlreadyExistsException extends Exception{

	public AttachmentAlreadyExistsException(String message) {
		super(message); //ATTACHMENT ALREADY EXISTS
	}
}