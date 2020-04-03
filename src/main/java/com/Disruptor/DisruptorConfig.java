package com.Disruptor;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @author Vic Zhang
 * @date 2020/3/11 5:19 PM
 */
@Configuration
public class DisruptorConfig {

    @Bean
    public RingBuffer<DisruptorEvent> ringBuffer() {
        ThreadFactory executor = Executors.defaultThreadFactory();
        DisruptorEventFactory disruptorEventFactory = new DisruptorEventFactory();
        Disruptor<DisruptorEvent> disruptor =
        new Disruptor<DisruptorEvent>(disruptorEventFactory, 256 * 1024, executor);
        disruptor.handleEventsWith(new DisruptorEventHandler());
        disruptor.start();
        RingBuffer<DisruptorEvent> ringBuffer = disruptor.getRingBuffer();
        return ringBuffer;
    }

}
