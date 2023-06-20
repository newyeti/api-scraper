package com.newyeti.apiscraper.common.error;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {

    List<Error> errors;

}