package com.Disruptor;

import com.Entity.MessageEntity;
import com.Service.AbstractDisruptorProcess;
import com.corundumstudio.socketio.SocketIOClient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Vic Zhang
 * @date 2020/3/11 2:20 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DisruptorEvent {

    private AbstractDisruptorProcess abstractDisruptorProcess;


    private SocketIOClient socketIOClient;


    private Object data;
}
