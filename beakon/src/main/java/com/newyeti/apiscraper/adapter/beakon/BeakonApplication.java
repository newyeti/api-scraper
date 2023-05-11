package com.newyeti.apiscraper.adapter.beakon;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@ComponentScan(basePackages = "com.newyeti.apiscraper.*")
@Slf4j
public class BeakonApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeakonApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			log.debug("Spring Boot Beans");
			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for (String beanName : beanNames) {
				log.debug(beanName);
			}
		};
	} 

}
