package com.joceano.kafkaconsumer.services;

import com.joceano.kafkaconsumer.models.Pedido;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "${topic.name.consumer}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(Pedido pedido){
        System.out.println(pedido);
    }

}
