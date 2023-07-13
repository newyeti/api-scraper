package com.newyeti.apiscraper.domain.port.spi;

public interface BusinessServiceSpi<T> {
    void postProcess(T t);
}
