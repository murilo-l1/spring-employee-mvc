package com.example.employeemvc.exception;

public class ErrorDetails {

    private Long timeStamp;
    private String message;
    private String cause;

    public ErrorDetails(Long timeStamp, String message, String cause) {
        this.timeStamp = timeStamp;
        this.message = message;
        this.cause = cause;
    }


}