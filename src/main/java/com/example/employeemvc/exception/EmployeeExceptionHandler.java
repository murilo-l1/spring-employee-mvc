package com.example.employeemvc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class EmployeeExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception e){
        ErrorDetails errorDetails = new ErrorDetails(new Date().getTime(), e.getMessage(), e.getCause().toString());
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<?> handleEmployeeNotFoundException(Exception ex){
        ErrorDetails errorDetails = new ErrorDetails(new Date().getTime(), ex.getMessage(), ex.getCause().toString());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
}