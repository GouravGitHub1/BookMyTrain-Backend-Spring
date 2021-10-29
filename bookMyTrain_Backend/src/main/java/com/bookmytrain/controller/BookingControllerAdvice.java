package com.bookmytrain.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.bookmytrain.model.BookingException;
import com.bookmytrain.model.ErrorResponse;

@RestControllerAdvice
public class BookingControllerAdvice {
	
	
	
	@ExceptionHandler(BookingException.class)
	private ResponseEntity<ErrorResponse> exceptionHandler(BookingException e) {
		ErrorResponse error = new ErrorResponse("Failure", e.getErrorMessage());
		return new ResponseEntity<> (error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	private ResponseEntity<ErrorResponse> exceptionHandler(Exception e) {
		ErrorResponse error = new ErrorResponse("Failure", e.getMessage());
		return new ResponseEntity<> (error, HttpStatus.BAD_REQUEST);
	}
	
}
