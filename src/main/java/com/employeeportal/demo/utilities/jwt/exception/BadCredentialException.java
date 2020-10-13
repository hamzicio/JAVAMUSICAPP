package com.employeeportal.demo.utilities.jwt.exception;

public class BadCredentialException extends Exception {
    public BadCredentialException(String message) {
        super(message);
    }
}
