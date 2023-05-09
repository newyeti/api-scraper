package com.newyeti.apiscraper.beakon.adapter.rest.standings;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.newyeti.apiscraper.domain.model.avro.schema.League;
import com.newyeti.apiscraper.domain.port.api.spi.kafka.AvroProducerPort;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name="standings", description = "Pull data from api and put in a kafka topic")
@RestController
@RequestMapping("standings")
@RequiredArgsConstructor
public class LeagueStandingsController {
    
    private AvroProducerPort<League> avroProducerPort;

    @GetMapping
    public void consumeAndSend() {
        
    }

}
