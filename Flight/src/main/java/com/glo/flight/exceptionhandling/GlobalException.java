package com.glo.flight.exceptionhandling;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.glo.flight.errors.ErrorDetails;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(FlightNotFoundException.class)
    public ResponseEntity<ErrorDetails> flightIdNotFound(FlightNotFoundException flightNotFoundException,
    		WebRequest request) {
    	
    	ErrorDetails errorObj = ErrorDetails.builder().description(request.getDescription(false)).
    			errorMessage(flightNotFoundException.getMessage())
    			.timestamp(LocalDateTime.now()).build();
    	
    	return new ResponseEntity<>(errorObj,HttpStatus.NOT_FOUND);
    			
    }
}
