package com.joceano.kafkaconsumer.services;

import com.joceano.kafkaconsumer.models.Pedido;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @Value("${topic.name.consumer")
    private String topicName;

    @KafkaListener(topics = "${topic.name.consumer}", groupId = "group_id")
    public void consume(Pedido pedido){
        System.out.println(pedido);
    }

}
