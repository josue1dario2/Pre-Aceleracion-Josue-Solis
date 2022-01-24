package com.alkemy.preaceleracion.exception;

import org.springframework.http.HttpStatus;

public class SpringException extends Exception{

    private HttpStatus status;

    public SpringException(String msg){
        super(msg);
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
    }
    public SpringException(String msg, HttpStatus status){
        super(msg);
        this.status = status;
    }
}
