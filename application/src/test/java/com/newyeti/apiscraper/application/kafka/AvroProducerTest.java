package com.newyeti.apiscraper.application.kafka;


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
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import com.newyeti.apiscraper.application.exception.AvroException;
import com.newyeti.apiscraper.domain.model.avro.schema.League;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest(classes = AvroProducerTest.class)
@ActiveProfiles("test")
@Import({AvroProducerTest.KafkaTestContainerConfiguration.class, AvroProducer.class, AvroConsumer.class, AvroConsumerErrorHandler.class})
@DirtiesContext
@Testcontainers
@Slf4j
public class AvroProducerTest {

    @Container
    public static KafkaContainer kafka = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:7.4.0"));

    @Autowired
    private AvroProducer avroProducer;
    @Autowired
    private AvroConsumer avroConsumer;

    @Before
    public void setup() {
        avroConsumer.resetLatch();
    }

    @Test
    public void givenKafkaContainer_whenSendingAvroMessage_thenMessageSent() throws Exception {
        League league = League.newBuilder()
            .setId(100)
            .setName("Premier League")
            .build();
        
        avroProducer.send("league.topic.v1", String.valueOf(league.getId()), league);
        avroConsumer.getLatch().await(5, TimeUnit.SECONDS);
        Assertions.assertNotNull(avroConsumer.getPayload());
    }

    // @Test
    // public void givenInvalidMessage_whenSendingMessage_thenThrowsException() throws Exception {

    //     try{
    //         avroProducer.send("league.topic.v1", "123", "invalid data");
    //         avroConsumer.getLatch().await(5, TimeUnit.SECONDS);
    //     } catch(Exception ex) {
    //         log.error("error", ex);
    //     }
        
    //     // Exception exception = Assertions.assertThrows(AvroException.class, () -> {
           
    //     // });
        
    //     // Assertions.assertEquals("avro consumer exception", exception.getMessage());
    // }

    @TestConfiguration
    @EnableKafka
    static class KafkaTestContainerConfiguration {

        @Bean
        ConcurrentKafkaListenerContainerFactory<Integer, String> kafkaListenerContainerFactory(ConsumerFactory<Integer, String> consumerFactory) {
            ConcurrentKafkaListenerContainerFactory<Integer, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
            factory.setConsumerFactory(consumerFactory);
            return factory;
        }

        @Bean
        public ConsumerFactory<Integer, String> consumerFactory() {
            return new DefaultKafkaConsumerFactory<>(consumerConfigs());
        }

        @Bean
        public Map<String, Object> consumerConfigs() {
            Map<String, Object> props = new HashMap<>();
            props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafka.getBootstrapServers());
            props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
            props.put(ConsumerConfig.GROUP_ID_CONFIG, "league-standings");
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
        public AvroProducer<League> avroProducer() {
            return new AvroProducer<>(kafkaTemplate());
        }

        @Bean
        public AvroConsumer avroConsumer() {
            return new AvroConsumer();
        }
    }

}
