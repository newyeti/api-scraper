package com.newyeti.apiscraper.infrastructure.kafka.standings;

import org.springframework.stereotype.Service;

import com.newyeti.apiscraper.domain.model.avro.schema.LeagueStandings;
import com.newyeti.apiscraper.domain.port.spi.standings.StandingsAvroProducerPort;
import com.newyeti.apiscraper.infrastructure.kafka.AvroProducerService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StandingsAvroProducerService implements StandingsAvroProducerPort{

    private final AvroProducerService<LeagueStandings> avroProducerService;

    @Override
    public boolean send(String topic, String id, LeagueStandings leaguesStandings) {
        return avroProducerService.send(topic, id, leaguesStandings);
    }
    
}
