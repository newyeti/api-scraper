package com.newyeti.apiscraper.domain.port.spi.standings;

import com.newyeti.apiscraper.domain.model.avro.schema.League;

public interface CreateStandingsJpaPort {
    void save(String key, League league);
}
