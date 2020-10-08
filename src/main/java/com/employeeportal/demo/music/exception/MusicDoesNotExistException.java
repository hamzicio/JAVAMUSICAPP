package com.employeeportal.demo.music.exception;

public class MusicDoesNotExistException extends Exception {
    public MusicDoesNotExistException(String message) {
        super(message);
    }
}
