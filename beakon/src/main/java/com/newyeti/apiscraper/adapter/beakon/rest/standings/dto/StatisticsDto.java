package com.newyeti.apiscraper.adapter.beakon.rest.standings.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatisticsDto {
    int played;
    int win;
    int draw;
    int lose;
    Goals goals;

    @Data
    public static class Goals {
        @JsonProperty("for")
        int goalFor;
        @JsonProperty(value="against")
        int goalAgainst;
    }
}
