package com.newyeti.apiscraper.infrastructure.jpa.mongo.league.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@Document("league")
public class LeagueEntity {
    
    @Id
    String id;

    int leagueId;
    String name;
    String country;
    String logo;
    String flag;

    @Transient
    int season;
    
    String uuid;
    String updatedOn;
    
    List<Integer> seasons;
}


