package com.newyeti.apiscraper.infrastructure.standings;

import java.util.Optional;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.newyeti.apiscraper.domain.model.avro.schema.League;
import com.newyeti.apiscraper.domain.port.spi.BusinessServiceSpi;
import com.newyeti.apiscraper.domain.port.spi.standings.CreateStandingsJpaPort;
import com.newyeti.apiscraper.infrastructure.kafka.AvroConsumerService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StandingsAvroConsumerService extends AvroConsumerService<String, League> {
    private final CreateStandingsJpaPort createStandingsJpaPort;
    private final BusinessServiceSpi<League> businessServiceSpi;
    
    @Override
    @KafkaListener(topics = "${avro.consumer.topics.standings}", 
        groupId = "${avro.consumer.groupId}", 
        errorHandler = "avroConsumerErrorHandler")
    public Optional<League> receive(ConsumerRecord<String, League> consumerRecord) {
       return super.receive(consumerRecord);
    }

    @Override
    public void process(League league) {   
        createStandingsJpaPort.save(league);
        businessServiceSpi.postProcessReceivedMessage(league);
    }

}
