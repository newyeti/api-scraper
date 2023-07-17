package com.newyeti.apiscraper.producer.rest.standings.dto;

import java.util.List;

import com.newyeti.apiscraper.common.error.Error;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseDto {
    List<SuccessResponseDto> success;
    List<Error> errors;
}
