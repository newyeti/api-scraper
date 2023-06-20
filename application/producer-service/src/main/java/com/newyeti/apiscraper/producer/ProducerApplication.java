package com.newyeti.apiscraper.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ComponentScan.Filter;

@SpringBootApplication(scanBasePackages = "com.newyeti.apiscraper")
@ComponentScan(basePackages = {"com.newyeti.apiscraper"},
	excludeFilters = @Filter(type=FilterType.ASPECTJ, 
		pattern = "com.newyeti.apiscraper.infrastructure.jpa.*"))
public class ProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProducerApplication.class, args);
	}

}
