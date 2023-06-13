package com.newyeti.apiscraper.infrastructure.standings;

import java.util.Optional;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.newyeti.apiscraper.domain.model.avro.schema.League;
import com.newyeti.apiscraper.domain.port.spi.standings.CreateStandingsJpaPort;
import com.newyeti.apiscraper.domain.services.standings.StandingsConsumerSpiService;
import com.newyeti.apiscraper.infrastructure.kafka.AvroConsumerService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class StandingsAvroConsumerService extends AvroConsumerService<String, League> {
    
    private final CreateStandingsJpaPort createStandingsJpaPort;
    private final StandingsConsumerSpiService standingsConsumerSpiService;
    
    @Override
    @KafkaListener(topics = "${avro.consumer.topics.standings}", 
        groupId = "${avro.consumer.groupId}", 
        errorHandler = "avroConsumerErrorHandler")
    public void receive(ConsumerRecord<String, League> consumerRecord) {
       super.receive(consumerRecord);
    }

    @Override
    public void process(League league) {   
        //createStandingsJpaPort.save(league);
        //standingsConsumerSpiService.postProcessReceivedMessage(league);
    }

}
