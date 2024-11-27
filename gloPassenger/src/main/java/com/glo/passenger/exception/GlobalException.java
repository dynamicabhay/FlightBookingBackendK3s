package com.glo.passenger.exception;

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

import com.glo.passenger.error.ErrorDetails;

@RestControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PassengerNotFoundException.class)
    public ResponseEntity<ErrorDetails> passengerIdNotFound(PassengerNotFoundException passengerNotFoundException,
    		WebRequest request) {
    	ErrorDetails errorDetails = ErrorDetails.builder().description(request.getDescription(false))
    			.errorMessage(passengerNotFoundException.getMessage())
    			.timestamp(LocalDateTime.now()).build();
        return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
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
