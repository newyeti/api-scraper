package com.newyeti.apiscraper.adapter.beakon.observation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.aop.ObservedAspect;

@Configuration
public class ObservedAspectConfiguration {

    @Bean
    public ObservationRegistry observationRegistry() {
      return ObservationRegistry.create();
    }

    @Bean
    public ObservedAspect observedAspect(ObservationRegistry observationRegistry) {
      observationRegistry.observationConfig().observationHandler(new SimpleLoggingHandler());
      return new ObservedAspect(observationRegistry);
    }

}
