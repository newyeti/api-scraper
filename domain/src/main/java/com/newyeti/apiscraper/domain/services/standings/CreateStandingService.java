package com.newyeti.apiscraper.domain.services.standings;

import org.springframework.stereotype.Service;

import com.newyeti.apiscraper.domain.model.avro.schema.League;
import com.newyeti.apiscraper.domain.port.api.BusinessServiceApi;
import com.newyeti.apiscraper.domain.port.api.standings.CreateStandginsApi;
import com.newyeti.apiscraper.domain.port.spi.kafka.AvroProducerPort;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateStandingService implements CreateStandginsApi, BusinessServiceApi {

    private final AvroProducerPort<League> avroProducerPort;

    @Override
    public void create(League league, String id, String topic) {
        avroProducerPort.send(topic, id, league);
        postProcessSendingMessage(true);
    }

    @Override
    public void postProcessSendingMessage(boolean success) {
        log.debug("send notification when failure");
    }
    
}
