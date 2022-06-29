package com.joceano.kafkaconsumer.models.exceptions;

public class KafkaException extends RuntimeException {

    public KafkaException(String message) {
        super(message);
    }
}
