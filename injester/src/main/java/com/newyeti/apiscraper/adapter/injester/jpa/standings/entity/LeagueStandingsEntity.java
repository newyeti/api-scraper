package com.newyeti.apiscraper.adapter.injester.jpa.standings.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
@Document
public class LeagueStandingsEntity {
    
    @Id
    String id;

    int leagueId;
    String name;
    String country;
    String logo;
    String flag;
    int season;
    List<StandingsEntity> standings;

}
