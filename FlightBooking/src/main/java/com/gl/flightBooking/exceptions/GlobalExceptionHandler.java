package com.gl.flightBooking.exceptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.gl.flightBooking.errors.ErrorDetails;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorDetails> ResourceNotFoundExceptionHandler(
			ResourceNotFoundException ex,
            WebRequest webRequest
			){
		ErrorDetails err = ErrorDetails.builder().timestamp(LocalDateTime.now()).errorMessage(ex.getMessage())
								.description(webRequest.getDescription(false)).build();
		return new ResponseEntity<ErrorDetails>(err,HttpStatus.NOT_FOUND);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
	        MethodArgumentNotValidException ex,
	        HttpHeaders headers,
	        HttpStatusCode status,
	        WebRequest request) {
	    Map<String,String> errors = new HashMap<>();
	    ex.getBindingResult().getAllErrors().forEach(
	            error -> {
	                String fieldName = ((FieldError)error).getField();
	                String message = error.getDefaultMessage();
	                errors.put(fieldName,message);
	            }
	    );
	    return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
	}
}
