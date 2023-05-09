package com.newyeti.apiscraper.domain.model.http.standings;

import java.util.List;

import lombok.Data;

@Data
public class StandingResponse {
    int results;
    List<Error> errors;
    List<League> response;
    
    @Data
    static class Error {
        String league;
        String team;
    }
    
}


