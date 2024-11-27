package com.glo.flight.exceptionhandling;

import lombok.AllArgsConstructor;
import lombok.Getter;

//@AllArgsConstructor
//@Getter
public class FlightNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;

	public FlightNotFoundException(String message) {
    	super(message);
    	this.message=message;
		// TODO Auto-generated constructor stub
	}

	private String message;

}
