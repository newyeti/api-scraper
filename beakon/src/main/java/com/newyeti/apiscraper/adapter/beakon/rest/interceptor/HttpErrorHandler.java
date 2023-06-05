package com.newyeti.apiscraper.adapter.beakon.rest.interceptor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.newyeti.apiscraper.adapter.beakon.rest.exception.ApiException;
import com.newyeti.apiscraper.adapter.beakon.rest.exception.ServiceException;
import com.newyeti.apiscraper.adapter.beakon.rest.response.ErrorResponse;
import com.newyeti.apiscraper.adapter.beakon.rest.response.Error;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class HttpErrorHandler {

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ErrorResponse> other(ServiceException exception) {
        log.error("service exception: httpstatus={}", exception.getHttpStatus().value(), exception);
        return ResponseEntity.status(exception.getHttpStatus())
                            .body(ErrorResponse.builder()
                                                .errors(exception.getErrors())
                                                .build());
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponse> other(ApiException exception) {
        log.error("api exception: httpstatus={} uri={}", exception.getHttpStatus().value(), exception.getUri());
        return ResponseEntity.status(exception.getHttpStatus())
                            .body(ErrorResponse.builder()
                                                .errors(exception.getErrors())
                                                .build());
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> other(Exception exception) {
        log.error("internal server error", exception);
        List<Error> errors = new ArrayList<>();
        errors.add(Error.builder()
            .code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
            .reason("request")
            .message("internal server error")
            .build());
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                                .body(ErrorResponse.builder()
                                .errors(errors)
                                .build());
    }

}
