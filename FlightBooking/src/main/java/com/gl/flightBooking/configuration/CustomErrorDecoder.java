package com.gl.flightBooking.configuration;

import com.gl.flightBooking.exceptions.ResourceNotFoundException;

import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomErrorDecoder implements ErrorDecoder{

	@Override
	public Exception decode(String methodKey, Response response) {
		switch (response.status()) {
       
        case 404:
            // Handle 404 Not Found
        	//System.out.println(methodKey);
            return new ResourceNotFoundException("flightId / passengerId / is not present");
        
        default:
            return new Exception("Generic error");
    }
	}

	
}
