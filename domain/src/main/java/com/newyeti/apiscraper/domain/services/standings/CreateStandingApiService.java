package com.newyeti.apiscraper.domain.services.standings;

import org.springframework.stereotype.Service;

import com.newyeti.apiscraper.common.exception.ServiceException;
import com.newyeti.apiscraper.domain.model.avro.schema.LeagueStandings;
import com.newyeti.apiscraper.domain.port.api.standings.CreateStandingsApi;
import com.newyeti.apiscraper.domain.port.spi.standings.CreateStandingsJpaPort;
import com.newyeti.apiscraper.domain.port.spi.standings.StandingsAvroProducerPort;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateStandingApiService implements CreateStandingsApi {

    private final StandingsAvroProducerPort standingsAvroProducerPort;
    private final CreateStandingsJpaPort createStandingsJpaPort;

    @Override
    public void send(LeagueStandings leagueStandings, String topic, String id) {
        standingsAvroProducerPort.send(topic, id, leagueStandings);
        postProcessSendingMessage(true);
    }

    @Override
    public void create(String key, LeagueStandings leagueStandings) throws ServiceException {
        createStandingsJpaPort.save(key, leagueStandings);
    }

    @Override
    public void postProcessSendingMessage(boolean success) {
        log.debug("send notification when failure");
    }

}
