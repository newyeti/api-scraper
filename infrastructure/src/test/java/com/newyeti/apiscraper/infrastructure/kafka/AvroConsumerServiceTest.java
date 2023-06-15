package com.newyeti.apiscraper.infrastructure.kafka;


import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.util.StringUtils;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.newyeti.apiscraper.domain.model.avro.schema.League;
import com.newyeti.apiscraper.infrastructure.jpa.mongo.standings.entity.LeagueStandingsEntity;
import com.newyeti.apiscraper.infrastructure.jpa.mongo.standings.repository.StandingsRepository;
import com.newyeti.apiscraper.infrastructure.kafka.standings.StandingsAvroConsumerService;

import static org.mockito.Mockito.*;

@SpringBootTest(classes = AvroConsumerServiceTest.class)
@ActiveProfiles("test")
@DirtiesContext
@Testcontainers
@ContextConfiguration(classes = {KafkaTestConfiguration.class})
@ComponentScan(basePackages = "com.newyeti.apiscraper")
@ExtendWith(MockitoExtension.class)
public class AvroConsumerServiceTest extends KafkaContainerTestConfiguration {

    @Autowired
    private AvroProducerService<League> avroProducerService;

    @Autowired
    private StandingsAvroConsumerService standingsAvroConsumerService;

    @Autowired
    private StandingsRepository standingsRepository;
    
    @BeforeEach
    public void setup() {
        standingsAvroConsumerService.resetLatch();
        standingsRepository.deleteAll();
    }
    
    @Test
    public void givenKafkaContainer_whenSendingAvroMessage_thenMessageSent() throws Exception {
        League league = League.newBuilder()
            .setId(500)
            .setSeason(2023)
            .setName("Premier League")
            .build();
        
        avroProducerService.send("apiscraper.standings.avro.topic.v1", String.valueOf(league.getId()), league);
        standingsAvroConsumerService.getLatch().await(5, TimeUnit.SECONDS);
        List<LeagueStandingsEntity> leagueStandingsEntities = standingsRepository.findByLeagueId(500); 
        // Assertions.assertTrue(leagueStandingsEntities.size() > 0);
        // Assertions.assertTrue(StringUtils.hasLength(leagueStandingsEntities.get(0).getId()));
        // Assertions.assertEquals(500, leagueStandingsEntities.get(0).getLeagueId());

    }

}
