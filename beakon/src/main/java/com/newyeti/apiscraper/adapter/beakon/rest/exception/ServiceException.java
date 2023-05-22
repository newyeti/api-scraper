package com.newyeti.apiscraper.adapter.beakon.rest.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.newyeti.apiscraper.adapter.beakon.rest.response.Error;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceException extends Exception{

    protected HttpStatus httpStatus;
    protected List<Error> errors;
    
    public ServiceException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
        errors().add(buildError(httpStatus.value(), message));
    }

    public ServiceException(HttpStatus httpStatus, String message, Throwable throwable) {
        super(message, throwable);
        this.httpStatus = httpStatus;
        errors().add(buildError(httpStatus.value(), message));
    }

    public ServiceException(HttpStatus httpStatus, Error error) {
        super(error.getMessage());
        this.httpStatus = httpStatus;
        errors().add(error);
    }

    public ServiceException(HttpStatus httpStatus, List<Error> errors) {
        this.httpStatus = httpStatus;
        this.errors = errors;
    }

    protected List<Error> errors() {
        if (errors == null) {
            errors = new ArrayList<>();
        }
        return errors;
    }

    protected Error buildError(int code, String message) {
        return Error.builder()
                .code(String.valueOf(code))
                .message(message)
                .build();
    }

}
