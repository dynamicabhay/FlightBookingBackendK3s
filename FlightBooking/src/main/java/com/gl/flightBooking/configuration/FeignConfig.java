package com.gl.flightBooking.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

	@Bean
	public CustomErrorDecoder getErrorDecoder() {
		return new CustomErrorDecoder();
	}
}
