package com.joceano.kafkaconsumer.config.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaProperties {

    @Value("${kafka.topic}")
    private String topic;

    @Value("${kafka.group-id}")
    private String groupId;

    @Value("${kafka.concurrency}")
    private Integer concurrency;

    @Value("${kafka.poll-timeout}")
    private Integer pollTimeout;

    @Value("${kafka.server-config}")
    private String serverConfig;

    @Value("${kafka.dlt.topic}")
    private String dltTopic;

    @Value("${kafka.retry.initial-interval}")
    private Integer retryInitialInterval;

    @Value("${kafka.retry.multiplier}")
    private Integer multiplier;

    @Value("${kafka.retry.max-interval}")
    private Integer maxInterval;

    @Value("${kafka.retry.max-retries}")
    private Integer maxRetries;

    public String getTopic() {
        return topic;
    }

    public String getGroupId() {
        return groupId;
    }

    public Integer getConcurrency() {
        return concurrency;
    }

    public Integer getPollTimeout() {
        return pollTimeout;
    }

    public String getServerConfig() {
        return serverConfig;
    }

    public String getDltTopic() {
        return dltTopic;
    }

    public Integer getRetryInitialInterval() {
        return retryInitialInterval;
    }

    public Integer getMultiplier() {
        return multiplier;
    }

    public Integer getMaxInterval() {
        return maxInterval;
    }

    public Integer getMaxRetries() {
        return maxRetries;
    }
}
