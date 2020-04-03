package com.Service;

import com.Disruptor.DisruptorProdutor;
import com.Entity.MessageEntity;
import com.Enum.Envent;
import com.alibaba.fastjson.JSONObject;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Vic Zhang
 * @date 2020/2/25 4:10 PM
 */
@Service
public class MessageService extends AbstractDisruptorProcess implements DataListener<Object> {

    @Autowired
    private DisruptorProdutor disruptorProdutor;

    @Override
    public void process(SocketIOClient client, Object data) {
        String jsonObject = JSONObject.toJSONString(data);
        MessageEntity messageEntity = JSONObject.parseObject(jsonObject, MessageEntity.class);
        System.out.println("seesionId222222:" + client.getSessionId() + messageEntity.getWords());
    }

    @Override
    public void onData(SocketIOClient socketIOClient, Object mess, AckRequest ackRequest) throws Exception {
        disruptorProdutor.publishEvent(this, socketIOClient, mess);
    }
}
