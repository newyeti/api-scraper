package com.newyeti.apiscraper.domain.model.http.standings;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Standings {
    int rank;
    Team team;
    int points;
    int goalDiff;
    String group;
    String form;
    String status;
    String description;
    Statistics all;
    Statistics home;
    Statistics away;
    String update;
}
