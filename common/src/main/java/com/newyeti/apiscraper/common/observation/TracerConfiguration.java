package com.newyeti.apiscraper.common.observation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.metrics.Meter;
import io.opentelemetry.api.trace.Tracer;

@Configuration
public class TracerConfiguration {
    
    @Value("otel.traces.api.version")
    private String tracesApiVersion;

    @Value("otel.metrics.api.version")
    private String metricsApiVersion;

    @Bean
    public Tracer tracer() {
        return  GlobalOpenTelemetry.getTracer("io.opentelemetry.traces",
                    tracesApiVersion);
    }

    public Meter meter() {
        return GlobalOpenTelemetry.meterBuilder("io.opentelemetry.metrics")
            .setInstrumentationVersion(metricsApiVersion)
            .build();
    }

}
