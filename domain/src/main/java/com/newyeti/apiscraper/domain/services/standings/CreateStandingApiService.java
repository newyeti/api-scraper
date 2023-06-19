package com.newyeti.apiscraper.domain.services.standings;

import org.springframework.stereotype.Service;

import com.newyeti.apiscraper.domain.model.avro.schema.League;
import com.newyeti.apiscraper.domain.port.api.standings.CreateStandingsApi;
import com.newyeti.apiscraper.domain.port.spi.standings.StandingsAvroProducerPort;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateStandingApiService implements CreateStandingsApi {

    private final StandingsAvroProducerPort standingsAvroProducerPort;

    @Override
    public void create(League league, String topic, String id) {
        standingsAvroProducerPort.send(topic, id, league);
        postProcessSendingMessage(true);
    }

    @Override
    public void postProcessSendingMessage(boolean success) {
        log.debug("send notification when failure");
    }
    
}