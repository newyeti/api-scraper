package com.newyeti.apiscraper.adapter.beakon.rest.response;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {

    List<Error> errors;

}