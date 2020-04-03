package com.Disruptor;

import com.Entity.MessageEntity;
import com.corundumstudio.socketio.SocketIOClient;
import com.lmax.disruptor.EventHandler;

/**
 * @author Vic Zhang
 * @date 2020/3/11 2:24 PM
 */
public class DisruptorEventHandler implements EventHandler<DisruptorEvent> {

    @Override
    public void onEvent(DisruptorEvent disruptorEvent, long l, boolean b) throws Exception {
        SocketIOClient so = disruptorEvent.getSocketIOClient();
        Object me = disruptorEvent.getData();
        disruptorEvent.getAbstractDisruptorProcess().process(so, me);
    }
}
