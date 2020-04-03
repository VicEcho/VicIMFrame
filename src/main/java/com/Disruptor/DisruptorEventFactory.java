package com.Disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * @author Vic Zhang
 * @date 2020/3/11 2:22 PM
 */
public class DisruptorEventFactory implements EventFactory {


    @Override
    public Object newInstance() {
        return new DisruptorEvent();
    }
}
