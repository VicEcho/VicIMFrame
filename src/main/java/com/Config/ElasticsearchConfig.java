package com.Config;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Setting;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.Transport;
import org.elasticsearch.transport.TransportChannel;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;

/**
 * @author Vic Zhang
 * @date 2020/3/31 10:50 AM
 */
@Configuration
public class ElasticsearchConfig {


    @Bean
    public TransportClient transportClient() {
        try {
            Settings setting = Settings.builder()
                    .put("cluster.name", "vicClustor")
                    .build();
            TransportClient client = new PreBuiltTransportClient(setting)
                    .addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300));
            return client;
        } catch (Exception e) {
            return null;
        }
    }
}
