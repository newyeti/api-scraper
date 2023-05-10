package com.newyeti.apiscraper.adapter.beakon.rest.standings.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StatisticsDto {
    int played;
    int win;
    int draw;
    int lose;
    Goals goals;

    @Data
    static class Goals {
        @JsonProperty("for")
        int goalFor;
        @JsonProperty(value="against")
        int goalAgainst;
    }
}
