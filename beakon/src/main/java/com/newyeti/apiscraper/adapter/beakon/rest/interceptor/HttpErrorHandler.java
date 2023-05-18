package com.newyeti.apiscraper.adapter.beakon.rest.interceptor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.newyeti.apiscraper.adapter.beakon.rest.exception.ServiceException;
import com.newyeti.apiscraper.adapter.beakon.rest.response.Error;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class HttpErrorHandler {

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<List<Error>> other(ServiceException exception) {
        log.error("service exception", exception);
        return ResponseEntity.status(exception.getHttpStatus()).body(exception.getErrors());
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> other(Exception exception) {
        log.error("internal server error", exception);
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT.value()).body("internal server error");
    }

}
