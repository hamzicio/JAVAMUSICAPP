package com.employeeportal.demo.music.exception;

public class AccessDeniedException extends Exception {

    public static final long serialVersionUID=1l;

    public AccessDeniedException(String message) {
        super(message);
    }
}
