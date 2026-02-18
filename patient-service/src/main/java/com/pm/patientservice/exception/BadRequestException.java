package com.pm.patientservice.exception;

public class BadRequestException extends Exception{
    public BadRequestException(String msg){
        super(msg);
    }
}
