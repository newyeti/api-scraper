package com.newyeti.apiscraper.adapter.beakon.rest.interceptor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HttpErrorHandler {
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> other(Exception exception) {
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT.value()).body(exception.getLocalizedMessage());
    }

}
