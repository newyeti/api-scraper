package com.newyeti.apiscraper.infrastructure.kafka;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.kafka.annotation.EnableKafka;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import com.newyeti.apiscraper.infrastructure.jpa.mongo.standings.repository.RepositoryContainerConfiguration;

@Testcontainers
@EnableKafka
public class KafkaContainerTestConfiguration extends RepositoryContainerConfiguration{
    
    @Container
    public static KafkaContainer kafka = new KafkaContainer(DockerImageName
        .parse("confluentinc/cp-kafka:7.4.0"));

    @BeforeAll
    static void beforeAll() {
        kafka.start();
    }

    @AfterAll
    static void afterAll() {
        kafka.stop();
    }
 
}
