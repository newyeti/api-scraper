package com.newyeti.apiscraper.domain.port.spi.standings;

import com.newyeti.apiscraper.domain.model.avro.schema.League;

public interface StandingsAvroProducerPort {
    void send(String topic, String id, League league);
    void postProcess(boolean success);
}
