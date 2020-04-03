package com.vic.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @author Vic Zhang
 * @date 2019/3/31 2:15 PM
 */
public class TestServerInitialzer extends ChannelInitializer<SocketChannel> {

    @Override
    protected  void initChannel(SocketChannel sc) throws Exception {
        ChannelPipeline channelPipeline = sc.pipeline();

        channelPipeline.addLast("HttpServer", new HttpServerCodec());

        channelPipeline.addLast("HttpServerHandler", new TestHttpServerHandler());
    };

}
