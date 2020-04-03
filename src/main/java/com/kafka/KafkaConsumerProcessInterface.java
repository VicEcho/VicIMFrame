package com.kafka;

/**
 * @author Vic Zhang
 * @date 2019/10/31 2:32 PM
 */
public interface KafkaConsumerProcessInterface {

    void process(String key, String message);
}
