package com.newyeti.apiscraper.adapter.beakon.rest.standings.mapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;

import com.newyeti.apiscraper.adapter.beakon.rest.standings.dto.StandingsDto;
import com.newyeti.apiscraper.domain.model.avro.schema.Standings;

@Mapper(config = StructMapperConfig.class)
public interface StandingsMapper {
        
    default List<Standings> mapToStandingList(List<List<StandingsDto>> value) {
        if (value == null) {
            return null;
        }

        if (value.isEmpty()) {
            return Collections.emptyList();
        }

        return value.stream()
            .flatMap(list -> list.stream())
            .map(s -> toStandings(s))
            .collect(Collectors.toList());
        
    }

    Standings toStandings(StandingsDto standingsDto);

}
