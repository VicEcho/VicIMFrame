package com.Server;

import com.Disruptor.DisruptorProdutor;
import com.Entity.MessageEntity;
import com.Enum.Envent;
import com.IM.Im.ImMessage;
import com.Service.AbstractDisruptorProcess;
import com.Service.MessageService;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import java.lang.Object;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Vic Zhang
 * @date 2020/2/24 3:20 PM
 */
@Component
public class Server {

    private MessageService messageService;


    @Autowired
    public Server(MessageService messageService) {
        this.messageService = messageService;
    }


    public void startServer() {
        Configuration config = new Configuration();
        config.setHostname("localhost");
        config.setPort(9098);
        SocketIOServer server = new SocketIOServer(config);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while(true) {
                    System.out.println("当前连接的socketClient" +  server.getAllClients());
                    try {
                        // sleep()：同步延迟数据，并且会阻塞线程
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();

        server.addEventListener(Envent.MESSAGEEVENT.toString(), Object.class, messageService);
        server.addEventListener(Envent.PACKETENVENT.toString(), byte[].class, new DataListener<byte[]>() {
            @Override
            public void onData(SocketIOClient client, byte[] data, AckRequest ackSender) throws Exception {
                ImMessage imMessage = ImMessage.parseFrom(data);
                System.out.println("imMessage.........." + imMessage);
            }
        });



        server.addConnectListener(new ConnectListener() {
            @Override
            public void onConnect(SocketIOClient client) {
                System.out.println("seesionId1111111:" + client.getSessionId());
                client.sendEvent(Envent.MESSAGEEVENT.toString(),  MessageEntity.builder().name("vic").words("hello").build());
            }
        });
        server.start();
    }


}
