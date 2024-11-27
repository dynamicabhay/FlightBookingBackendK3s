package com.glo.passenger.exception;

public class PassengerNotFoundException extends Throwable {
    private static final long serialVersionUID = 1L;

    public PassengerNotFoundException(String message) {
        super(message);
        this.message=message;
        // TODO Auto-generated constructor stub
    }

    private String message;
}
