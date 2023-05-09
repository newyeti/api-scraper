package com.newyeti.apiscraper.domain.model.http.standings;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Statistics {
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
