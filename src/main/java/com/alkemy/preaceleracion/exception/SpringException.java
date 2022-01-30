package com.alkemy.preaceleracion.exception;

import com.alkemy.preaceleracion.enums.Errors;
import org.springframework.http.HttpStatus;

public class SpringException extends RuntimeException{

    public SpringException(Errors error) { super(String.valueOf(error));}
}
