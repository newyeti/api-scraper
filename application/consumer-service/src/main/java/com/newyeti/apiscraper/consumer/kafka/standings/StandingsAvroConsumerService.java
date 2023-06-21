package com.newyeti.apiscraper.consumer.kafka.standings;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.newyeti.apiscraper.domain.model.avro.schema.LeagueStandings;
import com.newyeti.apiscraper.domain.port.spi.standings.CreateStandingsJpaPort;
import com.newyeti.apiscraper.domain.services.standings.StandingsConsumerSpiService;
import com.newyeti.apiscraper.infrastructure.kafka.AvroConsumerService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StandingsAvroConsumerService extends AvroConsumerService<String, LeagueStandings> {
    
    private final CreateStandingsJpaPort createStandingsJpaPort;
    private final StandingsConsumerSpiService standingsConsumerSpiService;
    
    @Override
    @KafkaListener(topics = "${avro.consumer.topics.standings}", 
        groupId = "${avro.consumer.groupId}", 
        errorHandler = "avroConsumerErrorHandler")
    public void receive(ConsumerRecord<String, LeagueStandings> consumerRecord) {
       super.receive(consumerRecord);
    }

    @Override
    public void process(String key, LeagueStandings leagueStandings) {   
        createStandingsJpaPort.save(key, leagueStandings);
        standingsConsumerSpiService.postProcessReceivedMessage(leagueStandings);
    }

}
