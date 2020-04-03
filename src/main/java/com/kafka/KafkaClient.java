package com.kafka;

import java.util.*;

import lombok.extern.slf4j.Slf4j;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;


/**
 * @author Vic Zhang
 * @date 2019/10/30 2:24 PM
 */
@Slf4j
public class KafkaClient {

    // 多态
    public void send(String topic, String value) {
        this.send(topic, value, UUID.randomUUID().toString());
    }

    public void send(String topic, String value, String key) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("acks", "all");
        props.put("key.serializer", StringSerializer.class.getName());
        props.put("value.serializer", StringSerializer.class.getName());
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(props);
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topic, key, value);
        kafkaProducer.send(producerRecord);
        kafkaProducer.close();
    }

    // 订阅模式
    public void consumerMessage(String topic, KafkaConsumerProcessInterface kafkaConsumerProcessInterface) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", topic);
        props.put("enable.auto.commit", true);
        props.put("session.timeout.ms", "20000");
        props.put("max.poll.interval.ms", "1000");
        props.put("key.deserializer", StringDeserializer.class.getName());
        props.put("value.deserializer", StringDeserializer.class.getName());

        try {
            KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(props);
            kafkaConsumer.subscribe(Arrays.asList(topic));
            ConsumerThread consumerThread = new ConsumerThread(kafkaConsumer, kafkaConsumerProcessInterface);
            consumerThread.setName(topic);
            consumerThread.start();
        } catch (Exception e) {
            log.error("kafka消费异常");
        }
    }

    // 广播模式
    public void broadcastMessage(String topic, KafkaConsumerProcessInterface kafkaConsumerProcessInterface, Integer threadNum) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", UUID.randomUUID().toString());
        props.put("enable.auto.commit", true);
        props.put("session.timeout.ms", "20000");
        props.put("max.poll.interval.ms", "1000");
        props.put("key.deserializer", StringDeserializer.class.getName());
        props.put("value.deserializer", StringDeserializer.class.getName());

        try {
            KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(props);
            kafkaConsumer.subscribe(Arrays.asList(topic));
            for(int i=0;i<threadNum;i++) {
                ConsumerThread consumerThread = new ConsumerThread(kafkaConsumer, kafkaConsumerProcessInterface);
                consumerThread.setName(topic);
                consumerThread.start();
            }
        } catch (Exception e) {
            log.error("kafka消费异常");
        }
    }


    public static void main(String[] args) {
        KafkaClient kafkaClient = new KafkaClient();
        //  kafkaClient.send(topic, value);
        for(int i = 0; i<100; i++) {
            kafkaClient.send("test", "vic" + i);
        }
    }
}
