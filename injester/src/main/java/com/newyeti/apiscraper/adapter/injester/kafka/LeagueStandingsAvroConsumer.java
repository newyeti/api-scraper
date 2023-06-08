package com.newyeti.apiscraper.adapter.injester.kafka;

import java.util.Objects;
import java.util.concurrent.CountDownLatch;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.newyeti.apiscraper.domain.model.avro.schema.League;
import com.newyeti.apiscraper.domain.port.api.standings.LeagueStandingsServicePort;
import com.newyeti.apiscraper.domain.port.spi.kafka.AvroConsumerPort;

import io.opentelemetry.instrumentation.annotations.WithSpan;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@Data
@Profile("docker")
@RequiredArgsConstructor
public class LeagueStandingsAvroConsumer implements AvroConsumerPort<String, League>{

    private final LeagueStandingsServicePort leagueStandingAppService;

    private CountDownLatch latch = new CountDownLatch(1);
    private League payload;

    @Value("${avro.topic.standings}")
    private String topic;

    @KafkaListener(topics = "${avro.topic.standings}", groupId = "league-standings", errorHandler = "avroConsumerErrorHandler")
    @WithSpan
    public void receive(ConsumerRecord<String, League> consumerRecord) {
        log.info("received payload from topic={}", topic);
        payload = consumerRecord.value();
        if (Objects.isNull(payload)) {
            log.error("received 'null' payload=League on topic={}");
        } else {
            leagueStandingAppService.saveToDb(payload);
        }
    }

    public void resetLatch() {
        latch = new CountDownLatch(1);
    }

}