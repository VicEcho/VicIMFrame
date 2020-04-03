package com.vic.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author Vic Zhang
 * @date 2019/3/31 2:04 PM
 */
public class TestServer {
    public static void main(String[] args) throws Exception {
        /**
         * 兩個死循環，第一個只是處理連接
         */
        EventLoopGroup boseGroup = new NioEventLoopGroup();

        /**
         * 處理逻辑
         */
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {

            ServerBootstrap serverBootstrap = new ServerBootstrap();

            serverBootstrap.group(boseGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new TestServerInitialzer());

            ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();
            // 等待服务端监听端口关闭
            channelFuture.channel().closeFuture().sync();
        } finally { // 优雅关闭
            boseGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }


    }
}
