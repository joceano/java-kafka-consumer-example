package com.joceano.kafkaconsumer.config;

import com.joceano.kafkaconsumer.config.properties.KafkaProperties;
import com.joceano.kafkaconsumer.models.Pedido;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.TopicPartition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties.AckMode;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.ExponentialBackOffWithMaxRetries;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@Configuration
public class KafkaConsumerConfig {

    private final KafkaProperties kafkaProperties;

    public KafkaConsumerConfig(KafkaProperties kafkaProperties) {
        this.kafkaProperties = kafkaProperties;
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<Object, Object>> kafkaListenerContainerFactory(
            KafkaOperations<Object, Object> template) {
        var factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setConcurrency(this.kafkaProperties.getConcurrency());
        factory.getContainerProperties().setPollTimeout(this.kafkaProperties.getPollTimeout());
        factory.getContainerProperties().setAckMode(AckMode.RECORD);
        factory.setCommonErrorHandler(this.defaultErrorHandler(template));
        return factory;
    }

    @Bean
    public ConsumerFactory<Object, Object> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(this.consumerConfigs());
    }

    private Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, this.kafkaProperties.getServerConfig());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        props.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, JsonDeserializer.class);
        props.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class.getName());
        props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, Pedido.class);
        return props;
    }


    private DefaultErrorHandler defaultErrorHandler(KafkaOperations<Object, Object> template) {
        return new DefaultErrorHandler(
            this.getDeadLetterPublishingRecoverer(template),
            this.getExponentialBackOffWithMaxRetries());
    }

    private DeadLetterPublishingRecoverer getDeadLetterPublishingRecoverer(KafkaOperations<Object, Object> template) {
        return new DeadLetterPublishingRecoverer(template,
            (rec, ex) -> new TopicPartition(this.kafkaProperties.getDltTopic(), rec.partition()));
    }

    private ExponentialBackOffWithMaxRetries getExponentialBackOffWithMaxRetries() {
        var retries = new ExponentialBackOffWithMaxRetries(this.kafkaProperties.getMaxRetries());
        retries.setInitialInterval(this.kafkaProperties.getRetryInitialInterval());
        retries.setMultiplier(this.kafkaProperties.getMultiplier());
        retries.setMaxInterval(this.kafkaProperties.getMaxInterval());
        return retries;
    }
}
