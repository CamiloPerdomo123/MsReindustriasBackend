package com.msreindustrias.securityjwt.application;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message, int httpStatus) {
        super(message);
    }
}
