package com.newyeti.apiscraper.infrastructure.jpa.mongo.standings.repository;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@DirtiesContext
public class RepositoryContainerConfiguration {

    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.0.10");

    @DynamicPropertySource
    static void mongoDbProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.host", mongoDBContainer::getHost);
        registry.add("spring.data.mongodb.port", mongoDBContainer::getFirstMappedPort);
        registry.add("spring.data.mongodb.database", () -> "testdb");
        registry.add("spring.data.mongodb.user", () -> "test_user");
        registry.add("spring.data.mongodb.password", () -> "test_password");
    }

    @BeforeAll
    static void setUp() {
        mongoDBContainer.start();;
    }

    @AfterAll
    void tearDown() {
        mongoDBContainer.stop();
    }
    
}
