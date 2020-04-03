package com.Service;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.InetAddress;

/**
 * @author Vic Zhang
 * @date 2020/3/31 1:55 PM
 */
public class ElasticsearchService {

    private final TransportClient client;


    @Autowired
    public ElasticsearchService(TransportClient client) {
        this.client = client;
    }

    public void searchMessage() {
        GetResponse response = client.prepareGet("person", "_doc", "3").get();
        System.out.println("查询到的消息为=====");
    }


}
