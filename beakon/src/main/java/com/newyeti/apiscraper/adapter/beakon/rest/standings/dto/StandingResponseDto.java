package com.newyeti.apiscraper.adapter.beakon.rest.standings.dto;

import java.util.List;

import lombok.Data;

@Data
public class StandingResponseDto {
    int results;
    List<Error> errors;
    List<LeagueDto> response;
    
    @Data
    static class Error {
        String league;
        String team;
    }
    
}


