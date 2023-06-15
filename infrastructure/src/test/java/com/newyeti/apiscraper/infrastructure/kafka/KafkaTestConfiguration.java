package com.newyeti.apiscraper.infrastructure.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;

import com.newyeti.apiscraper.domain.model.avro.schema.League;
import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;

public class KafkaTestConfiguration {
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
            props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaContainerTestConfiguration.kafka.getBootstrapServers());
            props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
            props.put(ConsumerConfig.GROUP_ID_CONFIG, "api-scraper");
            props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
            props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, CustomKafkaAvroDeserializer.class);
            props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "1");
            props.put(AbstractKafkaAvroSerDeConfig.AUTO_REGISTER_SCHEMAS, false);
            props.put(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, true);
            props.put("schema.registry.url", "mock://not-used");
            return props;
        }

        @Bean
        public ProducerFactory<String, League> producerFactory() {
            Map<String, Object> configProps = new HashMap<>();
            configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaContainerTestConfiguration.kafka.getBootstrapServers());
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

        // @Bean
        // public StandingsAvroConsumerService standingsAvroConsumerService() {
        //     return new StandingsAvroConsumerService(null, null);
        // }
}
