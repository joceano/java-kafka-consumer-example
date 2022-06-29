package com.joceano.kafkaconsumer.services;

import com.joceano.kafkaconsumer.models.Pedido;
import com.joceano.kafkaconsumer.models.exceptions.KafkaException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumerService.class);

    @KafkaListener(topics = "${kafka.topic}", groupId = "${kafka.group-id}")
    public void consume(Pedido pedido){
        LOGGER.info("Pedido: {}", pedido);
        throw new KafkaException("Exception for dead letter topic.");
    }

}
