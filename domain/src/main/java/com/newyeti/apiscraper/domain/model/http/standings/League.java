package com.newyeti.apiscraper.domain.model.http.standings;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class League {
    int id;
    String name;
    String country;
    String logo;
    String flag;
    int season;
    List<Standings> standings;
}
