package com.alkemy.preaceleracion.exception;

import org.springframework.http.HttpStatus;

public class SpringException extends RuntimeException{

    public SpringException(String error) { super(error);}
}
