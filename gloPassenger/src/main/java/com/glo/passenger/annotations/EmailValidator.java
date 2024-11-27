package com.glo.passenger.annotations;

import java.util.regex.Pattern;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<ValidEmail, String> {

	@Override
    public void initialize(ValidEmail constraintAnnotation) {
    }

	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		
		  String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9]+\\.[A-Za-z]{2,3}$";
	    Pattern pattern = Pattern.compile(EMAIL_PATTERN);
	    if (email == null) {
            return false; 
        }
        return pattern.matcher(email).matches();
		
	}

	

	
}
