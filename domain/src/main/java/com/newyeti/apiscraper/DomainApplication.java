package com.newyeti.apiscraper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@ComponentScan({"com.newyeti.apiscraper.*"})
public class DomainApplication 
{
    public static void main( String[] args ){
        SpringApplication.run(DomainApplication.class, args);
    }
}
