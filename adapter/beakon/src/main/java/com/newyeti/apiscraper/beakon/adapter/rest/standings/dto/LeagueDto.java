package com.newyeti.apiscraper.beakon.adapter.rest.standings.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeagueDto {
    int id;
    String name;
    String country;
    String logo;
    String flag;
    int season;
    List<StandingsDto> standings;
}
