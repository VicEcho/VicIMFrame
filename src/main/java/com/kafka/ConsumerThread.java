package com.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Vic Zhang
 * @date 2019/10/31 2:28 PM
 */
@Slf4j
public class ConsumerThread extends Thread{

//    private static final Logger logger = LoggerFactory.getLogger(ConsumerThread.class);

     KafkaConsumer<String, String> kafkaConsumer;

     KafkaConsumerProcessInterface kafkaConsumerProcessInterface;

     boolean running = true;

    public ConsumerThread( KafkaConsumer<String, String> kafkaConsumer, KafkaConsumerProcessInterface kafkaConsumerProcessInterface ) {
        this.kafkaConsumer= kafkaConsumer;
        this.kafkaConsumerProcessInterface = kafkaConsumerProcessInterface;

    }
    @Override
    public void run() {
        while (running) {
            ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofSeconds(5));
            if (null != records && records.count() > 0) {
                for(ConsumerRecord<String, String> record: records) {
                    try {
                        kafkaConsumerProcessInterface.process(record.key(), record.value());
                    } catch (Exception e) {
                        log.error("kafka处理消费异常", e);
                    }
                }
            }
        }
        log.info("线程正在停止" + Thread.currentThread().getName());
        this.kafkaConsumer.close();
    }


    public void close() {
        running = false;
    }
}
