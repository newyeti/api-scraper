package com.newyeti.apiscraper.beakon.adapter.kafka;

import org.springframework.stereotype.Component;

import com.newyeti.apiscraper.domain.model.avro.schema.League;
import com.newyeti.apiscraper.domain.port.api.spi.kafka.AvroProducerPort;

@Component
public class AvroProducerAdapter implements AvroProducerPort<League> {

    @Override
    public void send(League t) {
       
    }
    
}
