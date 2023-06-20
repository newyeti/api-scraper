package com.newyeti.apiscraper.application.rest.standings.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class LeagueStandingsDto {
    int id;
    String name;
    String country;
    String logo;
    String flag;
    int season;
    List<List<StandingsDto>> standings;
}
