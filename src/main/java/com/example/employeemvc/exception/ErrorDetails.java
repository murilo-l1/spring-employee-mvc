package com.example.employeemvc.exception;

public class ErrorDetails {

    private Long timeStamp;
    private String message;


    public ErrorDetails(Long timeStamp, String message) {
        this.timeStamp = timeStamp;
        this.message = message;
    }


}