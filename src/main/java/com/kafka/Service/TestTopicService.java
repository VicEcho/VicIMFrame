package com.kafka.Service;

import com.kafka.KafkaConsumerProcessInterface;
import org.springframework.stereotype.Service;

/**
 * @author Vic Zhang
 * @date 2019/10/31 3:44 PM
 */
@Service
public class TestTopicService implements KafkaConsumerProcessInterface {

    @Override
    public void process(String key, String value) {
        System.out.println("接收到消息===" +  value);
    }


}
