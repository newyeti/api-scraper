package com.newyeti.apiscraper.domain.services.standings;

import org.springframework.stereotype.Service;

import com.newyeti.apiscraper.domain.model.avro.schema.League;
import com.newyeti.apiscraper.domain.services.kafka.AvroConsumerService;

import io.opentelemetry.instrumentation.annotations.WithSpan;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StandingsConsumerService extends AvroConsumerService<String, League>{
    @Override
    @WithSpan
    public void postReceiveMessage(League payload) {
        log.debug("Message ready for post processing");
    }
}
