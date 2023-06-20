package com.newyeti.apiscraper.consumerservice.kafka;


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
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.StringUtils;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.newyeti.apiscraper.domain.model.avro.schema.LeagueStandings;
import com.newyeti.apiscraper.infrastructure.jpa.mongo.standings.entity.LeagueStandingsEntity;
import com.newyeti.apiscraper.consumerservice.RepositoryContainerConfiguration;
import com.newyeti.apiscraper.infrastructure.jpa.mongo.standings.repository.StandingsRepository;
import com.newyeti.apiscraper.infrastructure.kafka.KafkaTestConfiguration;
import com.newyeti.apiscraper.consumerservice.kafka.standings.StandingsAvroConsumerService;
import com.newyeti.apiscraper.infrastructure.kafka.AvroProducerService;

@SpringBootTest(classes = AvroConsumerServiceTest.class)
@ActiveProfiles("test")
@DirtiesContext
@Testcontainers
@Import({
    KafkaTestConfiguration.class,
    RepositoryContainerConfiguration.class
})
@ComponentScan(basePackages = "com.newyeti.apiscraper")
@ExtendWith(MockitoExtension.class)
public class AvroConsumerServiceTest extends KafkaContainerTestConfiguration {

    @Autowired
    private AvroProducerService<LeagueStandings> avroProducerService;

    @Autowired
    private StandingsAvroConsumerService standingsAvroConsumerService;

    @Autowired
    private StandingsRepository standingsRepository;
    
    @BeforeEach
    public void setup() {
        standingsAvroConsumerService.resetLatch();
    }
    
    @Test
    public void givenKafkaContainer_whenSendingAvroMessage_thenMessageSent() throws Exception {
        LeagueStandings leagueStandings = LeagueStandings.newBuilder()
            .setId(500)
            .setSeason(2023)
            .setName("Premier League")
            .build();
        
        avroProducerService.send("apiscraper.standings.avro.topic.v1", String.valueOf(leagueStandings.getId()), leagueStandings);
        standingsAvroConsumerService.getLatch().await(5, TimeUnit.SECONDS);
        Thread.sleep(5000);
        List<LeagueStandingsEntity> leagueStandingsEntities = standingsRepository.findAll(); 
        Assertions.assertTrue(leagueStandingsEntities.size() > 0);
        Assertions.assertTrue(StringUtils.hasLength(leagueStandingsEntities.get(0).getId()));
        Assertions.assertEquals(500, leagueStandingsEntities.get(0).getLeagueId());

    }

}
