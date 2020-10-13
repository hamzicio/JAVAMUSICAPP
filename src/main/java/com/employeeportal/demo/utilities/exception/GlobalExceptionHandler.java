package com.employeeportal.demo.utilities.exception;

import com.employeeportal.demo.music.exception.AccessDeniedException;
import com.employeeportal.demo.music.exception.MusicDoesNotExistException;
import com.employeeportal.demo.user.exception.AddNewUserException;
import com.employeeportal.demo.user.exception.UserNotFoundException;
import com.employeeportal.demo.utilities.jwt.exception.BadCredentialException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{



    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        CustomErrorDetails customErrorDetails=new CustomErrorDetails(new Date(),"Error From handle Method ArgumentNotValid",ex.getMessage());

        return new ResponseEntity<>(customErrorDetails,HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        CustomErrorDetails customErrorDetails=new CustomErrorDetails(new Date(),"Error Method Not Allowed",ex.getMessage());
        return new ResponseEntity<>(customErrorDetails,HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex,WebRequest webRequest)
    {
        CustomErrorDetails customErrorDetails= new CustomErrorDetails(new Date(), ex.getMessage(),webRequest.getDescription(false));

        return new ResponseEntity<>(customErrorDetails,HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(AddNewUserException.class)
    public final ResponseEntity<Object> handleUserExistsException(AddNewUserException ex,WebRequest webRequest)
    {
        CustomErrorDetails customErrorDetails= new CustomErrorDetails(new Date(), ex.getMessage(),webRequest.getDescription(false));

        return new ResponseEntity<>(customErrorDetails,HttpStatus.CONFLICT);

    }

    @ExceptionHandler(BadCredentialException.class)
    public final ResponseEntity<Object> handleBadCredentialException(BadCredentialException ex,WebRequest webRequest)
    {
        CustomErrorDetails customErrorDetails= new CustomErrorDetails(new Date(), ex.getMessage(),webRequest.getDescription(false));

        return new ResponseEntity<>(customErrorDetails,HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(MusicDoesNotExistException.class)
    public final ResponseEntity<Object> handleMusicNotFoundException(MusicDoesNotExistException ex,WebRequest webRequest)
    {
        CustomErrorDetails customErrorDetails= new CustomErrorDetails(new Date(), ex.getMessage(),webRequest.getDescription(false));

        return new ResponseEntity<>(customErrorDetails,HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(AccessDeniedException.class)
    public final ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex,WebRequest webRequest)
    {
        CustomErrorDetails customErrorDetails= new CustomErrorDetails(new Date(), ex.getMessage(),webRequest.getDescription(false));

        return new ResponseEntity<>(customErrorDetails,HttpStatus.METHOD_NOT_ALLOWED);

    }
}
