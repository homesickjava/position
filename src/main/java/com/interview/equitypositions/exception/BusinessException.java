package com.interview.equitypositions.exception;

public class BusinessException extends RuntimeException {
	
	private String message;
	private String code;
	
	public BusinessException(String code, String message) {
		super(message);
		this.code = code;
		this.message = message;
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
}
