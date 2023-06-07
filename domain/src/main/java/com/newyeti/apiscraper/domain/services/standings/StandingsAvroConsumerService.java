package com.newyeti.apiscraper.domain.services.standings;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import com.newyeti.apiscraper.domain.model.avro.schema.League;
import com.newyeti.apiscraper.domain.port.spi.kafka.AvroConsumerPort;

public class StandingsAvroConsumerService implements AvroConsumerPort<String, League>{

    @Override
    public void receive(ConsumerRecord<String, League> consumerRecord) {
        throw new UnsupportedOperationException("Unimplemented method 'receive'");
    }
    
}
