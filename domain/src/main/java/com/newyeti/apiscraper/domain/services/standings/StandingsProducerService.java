package com.newyeti.apiscraper.domain.services.standings;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.newyeti.apiscraper.domain.model.avro.schema.League;
import com.newyeti.apiscraper.domain.services.kafka.AvroProducerService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StandingsProducerService extends AvroProducerService<League> {

    @Value("${app.kafka.send.enabled:false}")
    private boolean shouldSendToKafka;

    public StandingsProducerService(KafkaTemplate<String, League> kafkaTemplate) {
        super(kafkaTemplate);
    }

    @Override
    public void postSendMessage(boolean status) {
        // TO-DO send email on failure
        log.debug("Message sent. Added notification here if necessary");
    }

    @Override
    public boolean send(String topic, String id, League obj) {
        if(shouldSendToKafka) {
            return super.send(topic, id, obj);
        } else {
            log.info("Kafka send flag is disabled. Message will not be sent to Kafka topic." + 
                " If you intend to send the message, please enable flag for property '{}'",
                 "app.kafka.send.enabled");
            return true;
        }
        
    }
    
}
