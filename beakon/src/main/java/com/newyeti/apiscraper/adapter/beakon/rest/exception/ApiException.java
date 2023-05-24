package com.newyeti.apiscraper.adapter.beakon.rest.exception;

import lombok.Getter;
import lombok.Setter;

import org.springframework.http.HttpStatus;

import com.newyeti.apiscraper.adapter.beakon.rest.response.Error;

@Getter
@Setter
public class ApiException extends ServiceException{

    String uri;

    public ApiException(HttpStatus httpStatus, String uri, String message) {
        super(httpStatus, message);
        this.uri = uri;
        errors().add(buildError(httpStatus.value(), message));
    }

    public ApiException(HttpStatus httpStatus, String uri, String message, Throwable throwable) {
        super(httpStatus, message, throwable);
        this.uri = uri;
        errors().add(buildError(httpStatus.value(), message));
    }

    public ApiException(HttpStatus httpStatus, String uri , Error error) {
        super(httpStatus, error);
        this.uri = uri;
        errors().add(error);
    }
}
