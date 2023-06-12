package com.newyeti.apiscraper.infrastructure;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.newyeti.apiscraper.domain.model.avro.schema.League;
import com.newyeti.apiscraper.domain.port.spi.standings.StandingsAvroProducerPort;
import com.newyeti.apiscraper.infrastructure.kafka.AvroProducerService;
import com.newyeti.apiscraper.infrastructure.standings.StandingsAvroProducerService;

@SpringBootTest
// @ComponentScan(basePackages = {"com.newyeti.apiscraper"})
class InfrastructureApplicationTests {

	@Test
	void contextLoads() {
	}

	// @Autowired
	// private AvroProducerService<League> avroProducerService;

	// @Bean
	// public StandingsAvroProducerPort standingsAvroProducerService(){
	// 	return new StandingsAvroProducerService(avroProducerService);
	// }

}
