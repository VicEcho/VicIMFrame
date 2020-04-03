package com.kafka;

import com.kafka.Service.TestTopicService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Vic Zhang
 * @date 2019/10/30 5:32 PM
 */
public class ConsumerTest {

//    @Autowired
//    private TestTopicService testTopicService;

    public static void main(String[] args) {
        TestTopicService testTopicService = new TestTopicService();
        KafkaClient kafkaClient = new KafkaClient();
        kafkaClient.consumerMessage("test", testTopicService);
    }
}
