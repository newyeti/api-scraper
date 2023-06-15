package com.newyeti.apiscraper.infrastructure.jpa.mongo.standings.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatisticsEntity {
    int played;
    int win;
    int draw;
    int lose;
    GoalsEntity goals;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GoalsEntity {
        int goalsFor;
        int goalsAgainst;
    }
}

