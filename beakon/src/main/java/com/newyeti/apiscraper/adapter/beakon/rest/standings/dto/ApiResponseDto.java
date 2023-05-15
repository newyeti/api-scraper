package com.newyeti.apiscraper.adapter.beakon.rest.standings.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


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
    public static class Value {
        String season;
        String league;
    }

    @Data
    public static class Response {
        private LeagueDto league;  
    }

}
