package com.fabiopetricola.resources.exception;

public class AuthException extends Exception{
	
	private String message;
	
	public AuthException() {
		
	}
	public AuthException(String message) {
		super();
		this.message=message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}
