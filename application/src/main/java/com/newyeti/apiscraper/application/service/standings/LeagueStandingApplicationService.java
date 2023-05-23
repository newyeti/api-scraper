package com.newyeti.apiscraper.application.service.standings;

import org.springframework.stereotype.Service;

import com.newyeti.apiscraper.domain.model.avro.schema.League;
import com.newyeti.apiscraper.domain.port.api.spi.kafka.AvroProducerPort;
import com.newyeti.apiscraper.domain.port.api.standings.LeagueStandingsServicePort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LeagueStandingApplicationService implements LeagueStandingsServicePort{

    private final AvroProducerPort<League> avroProducerPort;

    @Override
    public void send(String topic, League league) {
        avroProducerPort.send(topic, league);
    }
    
}
