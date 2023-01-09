package com.prospecta.Exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyErrorList> ExceptionHandler(Exception ex,WebRequest webRequest) {
		MyErrorList error = new MyErrorList();
		error.setTime(LocalDateTime.now());
		error.setDetails(ex.getMessage());
		error.setMessage(webRequest.getDescription(false));
		return new ResponseEntity<MyErrorList>(error,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler()
	public ResponseEntity<MyErrorList> CustomExceptionHandler(CustomException ce,WebRequest wr){
		MyErrorList error = new MyErrorList();
		error.setTime(LocalDateTime.now());
		error.setDetails(ce.getMessage());
		error.setMessage(wr.getDescription(false));
		return new ResponseEntity<MyErrorList>(error,HttpStatus.BAD_REQUEST);
	}
}
