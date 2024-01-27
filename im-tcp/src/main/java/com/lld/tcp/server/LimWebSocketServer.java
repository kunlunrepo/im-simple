package com.lld.tcp.server;

import com.lld.codec.WebSocketMessageDecoder;
import com.lld.codec.WebSocketMessageEncoder;
import com.lld.tcp.config.BootStrapConfig;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * description : websocket服务器
 *
 * @author kunlunrepo
 * date :  2024-01-26 14:53
 */
public class LimWebSocketServer {

    // 主线程
    EventLoopGroup mainGroup;
    // 工作线程
    EventLoopGroup workGroup;
    // 启动服务
    ServerBootstrap server;
    // 配置
    BootStrapConfig.TcpConfig config;

    public LimWebSocketServer(BootStrapConfig.TcpConfig config) {
        this.config = config;
        mainGroup = new NioEventLoopGroup();
        workGroup = new NioEventLoopGroup();
        server = new ServerBootstrap();
        server.group(mainGroup, workGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024) // 服务端可连接队列长度
                .childOption(ChannelOption.SO_REUSEADDR, true) // 是否允许端口复用
                .childOption(ChannelOption.TCP_NODELAY, true) // 是否批量发送数据
                .childOption(ChannelOption.SO_KEEPALIVE, true) // 是否保持长连接 2小时没有数据会发送心跳
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel channel) throws Exception {
                        // HTTP编码解码器
                        channel.pipeline().addLast("http-codec", new HttpServerCodec());
                        // 写大数据流的支持
                        channel.pipeline().addLast("http-chunked", new ChunkedWriteHandler());
                        // 用于处理HTTP请求和响应的分块编码（Chunked Encoding）问题
                        channel.pipeline().addLast("aggregator", new HttpObjectAggregator(65536));
                        // websocket 服务器处理的协议，用于指定给客户端连接访问的路由 处理握手动作（close, ping, pong）
                        channel.pipeline().addLast(new WebSocketServerProtocolHandler("/ws"));
                        // websocket 消息解码
                        channel.pipeline().addLast(new WebSocketMessageDecoder());
                        // websocket 消息编码
                        channel.pipeline().addLast(new WebSocketMessageEncoder());
                        // 业务逻辑
                    }
                });
    }

    // 启动服务
    public void start() {
        this.server.bind(this.config.getWebSocketPort());
    }
}
