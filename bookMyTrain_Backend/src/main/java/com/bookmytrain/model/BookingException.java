package com.bookmytrain.model;

public class BookingException extends Exception {
	private static final long serialVersionUID = 1L;
	
	private String errorMessage;

	public String getErrorMessage() {
		return errorMessage;
	}

	public BookingException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}

	public BookingException() {
		super();
	}
	
	
}
