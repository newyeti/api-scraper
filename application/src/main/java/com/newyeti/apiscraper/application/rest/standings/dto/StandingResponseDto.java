package com.newyeti.apiscraper.application.rest.standings.dto;

import java.util.List;

import lombok.Data;

@Data
public class StandingResponseDto {
    int results;
    List<Error> errors;
    List<LeagueStandingsDto> response;
    
    @Data
    static class Error {
        String league;
        String team;
    }
    
}


