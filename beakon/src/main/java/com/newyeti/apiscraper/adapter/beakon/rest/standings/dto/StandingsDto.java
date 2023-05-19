package com.newyeti.apiscraper.adapter.beakon.rest.standings.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StandingsDto {
    int rank;
    TeamDto team;
    int points;
    int goalsDiff;
    String group;
    String form;
    String status;
    String description;
    StatisticsDto all;
    StatisticsDto home;
    StatisticsDto away;
    String update;
}
