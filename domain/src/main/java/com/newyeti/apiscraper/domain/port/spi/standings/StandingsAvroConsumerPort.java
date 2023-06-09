package com.newyeti.apiscraper.domain.port.spi.standings;

import com.newyeti.apiscraper.domain.model.avro.schema.League;

public interface StandingsAvroConsumerPort {
    void receive(String topic, League league);
    void postProcess(League league);
}
