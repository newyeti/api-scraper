package com.newyeti.apiscraper.infrastructure.kafka;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import com.newyeti.apiscraper.domain.model.avro.schema.League;

@SpringBootTest(classes = AvroConsumerServiceTest.class)
@ActiveProfiles("test")
@Import({AvroConsumerServiceTest.KafkaTestContainerConfiguration.class, AvroProducerService.class, AvroConsumerService.class, AvroConsumerErrorHandler.class})
@DirtiesContext
@Testcontainers
public class AvroConsumerServiceTest {

    @Container
    public static KafkaContainer kafka = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:7.4.0"));

    @Autowired
    private AvroProducerService<League> avroProducerService;
    @Autowired
    private AvroConsumerService<String, League> avroConsumerService;

    @Before
    public void setup() {
        avroConsumerService.resetLatch();
    }

    @Test
    public void givenKafkaContainer_whenSendingAvroMessage_thenMessageSent() throws Exception {
        League league = League.newBuilder()
            .setId(100)
            .setName("Premier League")
            .build();
        
        avroProducerService.send("apiscraper.standings.avro.topic.v1", String.valueOf(league.getId()), league);
        avroConsumerService.getLatch().await(5, TimeUnit.SECONDS);
        Assertions.assertNotNull(avroConsumerService.getPayload());
    }

    @TestConfiguration
    @EnableKafka
    static class KafkaTestContainerConfiguration {

        @Bean
        ConcurrentKafkaListenerContainerFactory<String, League> kafkaListenerContainerFactory(ConsumerFactory<String, League> consumerFactory) {
            ConcurrentKafkaListenerContainerFactory<String, League> factory = new ConcurrentKafkaListenerContainerFactory<>();
            factory.setConsumerFactory(consumerFactory);
            factory.setCommonErrorHandler(new DefaultErrorHandler());
            return factory;
        }

        @Bean
        public ConsumerFactory<String, League> consumerFactory() throws Exception{
            ErrorHandlingDeserializer<League> errorHandlingDeserializer
                = new ErrorHandlingDeserializer(new CustomKafkaAvroDeserializer());
            return new DefaultKafkaConsumerFactory<>(consumerConfigs(), new StringDeserializer(), errorHandlingDeserializer);
        }

        @Bean
        public Map<String, Object> consumerConfigs() {
            Map<String, Object> props = new HashMap<>();
            props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafka.getBootstrapServers());
            props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
            props.put(ConsumerConfig.GROUP_ID_CONFIG, "api-scraper");
            props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
            props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, CustomKafkaAvroDeserializer.class);
            props.put("schema.registry.url", "mock://not-used");
            return props;
        }

        @Bean
        public ProducerFactory<String, League> producerFactory() {
            Map<String, Object> configProps = new HashMap<>();
            configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafka.getBootstrapServers());
            configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
            configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, CustomKafkaAvroSerializer.class);
            configProps.put("schema.registry.url", "mock://not-used");
            return new DefaultKafkaProducerFactory<>(configProps);
        }

        @Bean
        public KafkaTemplate<String, League> kafkaTemplate() {
            return new KafkaTemplate<>(producerFactory());
        }

        @Bean
        public AvroProducerService<League> avroProducer() {
            return new AvroProducerService<>(kafkaTemplate());
        }

        @Bean
        public AvroConsumerService<String, League> avroConsumerService() {
            return new AvroConsumerService<>();
        }
    }

}
