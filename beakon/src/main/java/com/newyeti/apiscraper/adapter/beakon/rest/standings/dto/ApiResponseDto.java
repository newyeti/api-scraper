package com.newyeti.apiscraper.adapter.beakon.rest.standings.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.newyeti.apiscraper.domain.model.avro.schema.League;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class ApiResponseDto {
    
    private String get;
    private Value parameters;
    private List<Value> errors;
    private int results;
    private List<Response> response;   

    @Data
    static class Value {
        String season;
        String league;
    }

    @Data
    static class Response {
        private League league;  
    }

}
