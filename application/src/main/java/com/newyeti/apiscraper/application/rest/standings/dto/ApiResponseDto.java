package com.newyeti.apiscraper.application.rest.standings.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)
public class ApiResponseDto {
    
    private String get;
    private Value parameters;
    private List<Value> errors;
    private int results;
    private List<Response> response;   

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Value {
        String season;
        String league;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown=true)
    public static class Response {
        private LeagueStandingsDto leagueStandingsDto;  
    }

}
