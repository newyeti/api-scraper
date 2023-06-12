package com.newyeti.apiscraper.infrastructure.standings;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.newyeti.apiscraper.domain.model.avro.schema.League;
import com.newyeti.apiscraper.domain.port.spi.standings.StandingsAvroProducerPort;
import com.newyeti.apiscraper.infrastructure.kafka.AvroProducerService;

import lombok.RequiredArgsConstructor;

@Component("standingsAvroProducerPort")
@RequiredArgsConstructor
public class StandingsAvroProducerService implements StandingsAvroProducerPort{

    private final AvroProducerService<League> avroProducerService;

    @Override
    public boolean send(String topic, String id, League league) {
        return avroProducerService.send(topic, id, league);
    }
    
}
