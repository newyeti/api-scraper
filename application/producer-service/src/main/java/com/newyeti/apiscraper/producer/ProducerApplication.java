package com.newyeti.apiscraper.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.newyeti.apiscraper.common", 
	"com.newyeti.apiscraper.domain", 
	"com.newyeti.apiscraper.infrastructure.kafka",
	"com.newyeti.apiscraper.producer"})
public class ProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProducerApplication.class, args);
	}

}
