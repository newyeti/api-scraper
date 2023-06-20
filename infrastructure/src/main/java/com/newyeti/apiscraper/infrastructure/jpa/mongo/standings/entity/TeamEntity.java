package com.newyeti.apiscraper.infrastructure.jpa.mongo.standings.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeamEntity {
    int id;
    String name;
    String logo;
}
