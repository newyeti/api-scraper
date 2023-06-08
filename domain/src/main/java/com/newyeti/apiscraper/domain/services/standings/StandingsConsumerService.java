package com.newyeti.apiscraper.domain.services.standings;

import org.springframework.stereotype.Service;

import com.newyeti.apiscraper.domain.model.avro.schema.League;
import com.newyeti.apiscraper.domain.services.kafka.AvroConsumerService;

@Service
public class StandingsConsumerService extends AvroConsumerService<String, League>{
    @Override
    public void postReceiveMessage(League payload) {
       
    }
}
