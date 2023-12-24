package com.spring_basic_auth.basic_auth.exception.car_exceptions;

public class CarNotFoundException extends RuntimeException {

    public CarNotFoundException(String message) {
        super(message);
    }
}
