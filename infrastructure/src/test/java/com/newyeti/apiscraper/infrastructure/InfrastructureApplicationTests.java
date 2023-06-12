package com.newyeti.apiscraper.infrastructure;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

@SpringBootTest
@ComponentScan({"com.newyeti.apiscraper.domain.*"})
class InfrastructureApplicationTests {

	@Test
	void contextLoads() {
	}

}
