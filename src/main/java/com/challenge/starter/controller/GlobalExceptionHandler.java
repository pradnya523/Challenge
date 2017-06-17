package com.challenge.starter.controller;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.challenge.starter.domain.ErrorMessage;
import com.challenge.starter.domain.ParamException;

@ControllerAdvice
@ComponentScan("com.challenge.starter")
public class GlobalExceptionHandler {
   
	/**
	 * This function will handle any Exception thrown in application.
	 * 
	 */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> exceptionHandler(Exception ex) {

	ErrorMessage error = new ErrorMessage();

        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

        error.setErrMessage("Exception occured. ");
        
        error.setText(ex.getMessage());

        return new ResponseEntity<ErrorMessage>(error, HttpStatus.INTERNAL_SERVER_ERROR);

    }
    
    /**
	 * This function will handle ParamException thrown from the interceptor.
	 * 
	 */
    
    @ExceptionHandler(ParamException.class)
    public ResponseEntity<ErrorMessage> exceptionHandler(ParamException ex) {

	ErrorMessage error = new ErrorMessage();

        error.setStatus(HttpStatus.BAD_REQUEST.value());

        error.setErrMessage("Exception occured. ");
        
        error.setText(ex.getMessage());

        return new ResponseEntity<ErrorMessage>(error, HttpStatus.BAD_REQUEST);

    }
   
}

