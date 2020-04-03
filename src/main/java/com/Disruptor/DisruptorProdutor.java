package com.Disruptor;

import com.Entity.MessageEntity;
import com.Service.AbstractDisruptorProcess;
import com.corundumstudio.socketio.SocketIOClient;
import com.lmax.disruptor.RingBuffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.ByteBuffer;

/**
 * @author Vic Zhang
 * @date 2020/3/11 2:50 PM
 */
@Service
public class DisruptorProdutor {

    private final RingBuffer<DisruptorEvent> ringBuffer;

    @Autowired
    public DisruptorProdutor(RingBuffer<DisruptorEvent> ringBuffer) { this.ringBuffer = ringBuffer; }

    public void publishEvent(AbstractDisruptorProcess ab, SocketIOClient so, Object me) {
        long seq = ringBuffer.next();
        try {
            DisruptorEvent disruptorEvent = ringBuffer.get(seq);
            disruptorEvent.setAbstractDisruptorProcess(ab);
            disruptorEvent.setSocketIOClient(so);
            disruptorEvent.setData(me);
        } finally {
            ringBuffer.publish(seq);
        }
    }

}
