package com.newyeti.apiscraper.adapter.beakon.rest.standings.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class StatisticsDto {
    int played;
    int win;
    int draw;
    int lose;
    Goals goals;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Goals {
        @JsonProperty("for")
        int goalsFor;
        @JsonProperty(value="against")
        int goalsAgainst;
    }
}
