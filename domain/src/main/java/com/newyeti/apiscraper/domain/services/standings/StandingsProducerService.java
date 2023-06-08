package com.newyeti.apiscraper.domain.services.standings;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.newyeti.apiscraper.domain.model.avro.schema.League;
import com.newyeti.apiscraper.domain.services.kafka.AvroProducerService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StandingsProducerService extends AvroProducerService<League>{

    public StandingsProducerService(KafkaTemplate<String, League> kafkaTemplate) {
        super(kafkaTemplate);
    }

    @Override
    public void postSendMessage(boolean status) {
        // TO-DO send email on failure
        log.debug("Message sent. Added notification here if necessary");
    }
    
}
