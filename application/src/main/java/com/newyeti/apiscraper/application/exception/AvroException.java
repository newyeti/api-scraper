package com.newyeti.apiscraper.application.exception;

import org.apache.kafka.common.KafkaException;

public class AvroException extends KafkaException{
    public AvroException(String message, Throwable cause) {
        super(message, cause);
    }

    public AvroException(String message) {
        super(message);
    }

    public AvroException(Throwable cause) {
        super(cause);
    }

    public AvroException() {
        super();
    }
}
