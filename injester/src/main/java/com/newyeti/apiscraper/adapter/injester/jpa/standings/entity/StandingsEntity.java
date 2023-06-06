package com.newyeti.apiscraper.adapter.injester.jpa.standings.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StandingsEntity {
    int rank;
    TeamEntity team;
    int points;
    int goalsDiff;
    String group;
    String form;
    String status;
    String description;
    StatisticsEntity all;
    StatisticsEntity home;
    StatisticsEntity away;
    String update;
}
