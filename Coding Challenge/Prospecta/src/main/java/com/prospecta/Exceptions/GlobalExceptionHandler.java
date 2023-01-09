package com.prospecta.Exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

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
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyErrorList> methodArgsInvalid(MethodArgumentNotValidException e,WebRequest wr) {
		MyErrorList err = new MyErrorList();
		err.setTime(LocalDateTime.now());
		err.setMessage("Validation Error");
		err.setDetails(e.getBindingResult().getFieldError().getDefaultMessage());
	
		return new ResponseEntity<MyErrorList>(err,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<MyErrorList> noHandleException(NoHandlerFoundException e,WebRequest req){
		MyErrorList err = new MyErrorList();
		err.setDetails(req.getDescription(false));
		err.setTime(LocalDateTime.now());
		err.setMessage(e.getMessage());
		return new ResponseEntity<MyErrorList>(err,HttpStatus.BAD_REQUEST);
	}
	
	
	//mannual exception
	
	@ExceptionHandler(EntryException.class)
	public ResponseEntity<MyErrorList> PostException(EntryException e,WebRequest req){
		MyErrorList err = new MyErrorList();
		err.setDetails(req.getDescription(false));
		err.setTime(LocalDateTime.now());
		err.setMessage(e.getMessage());
		return new ResponseEntity<MyErrorList>(err,HttpStatus.BAD_REQUEST);
	}
	
	
}
