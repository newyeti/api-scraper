package com.newyeti.apiscraper.domain.services.standings;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.newyeti.apiscraper.domain.model.avro.schema.League;
import com.newyeti.apiscraper.domain.services.kafka.AvroProducerService;

@Service
public class StandingsProducerService extends AvroProducerService<League>{

    public StandingsProducerService(KafkaTemplate<String, League> kafkaTemplate) {
        super(kafkaTemplate);
    }

    @Override
    public void postSendMessage(boolean status) {
        // TO-DO send email on failure
    }
    
}
