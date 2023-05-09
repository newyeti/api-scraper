package com.newyeti.apiscraper.application.service.standings.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StandingsDto {
    int rank;
    TeamDto team;
    int points;
    int goalDiff;
    String group;
    String form;
    String status;
    String description;
    StatisticsDto all;
    StatisticsDto home;
    StatisticsDto away;
    String update;
}
