package com.newyeti.apiscraper.domain.port.spi;

public interface BusinessServiceSpi<T> {
    void postProcessReceivedMessage(T t);
}
