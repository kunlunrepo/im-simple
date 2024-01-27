package com.lld.tcp.server;

import com.lld.codec.MessageDecoder;
import com.lld.codec.MessageEncoder;
import com.lld.tcp.config.BootStrapConfig;
import com.lld.tcp.handler.HeartBeatHandler;
import com.lld.tcp.handler.NettyServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * description : IM服务
 *
 * @author kunlunrepo
 * date :  2024-01-26 14:25
 */
@Slf4j
public class LimServer {

    // 主线程
    EventLoopGroup mainGroup;
    // 工作线程
    EventLoopGroup workGroup;
    // 启动服务
    ServerBootstrap server;
    // 配置
    BootStrapConfig.TcpConfig config;

    public LimServer(BootStrapConfig.TcpConfig config) {
        // 获取配置
        this.config = config;
        // 初始化线程组
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
                        // 消息解码
                        channel.pipeline().addLast(new MessageDecoder());
                        // 消息编码
                        channel.pipeline().addLast(new MessageEncoder());
                        // 心跳处理器
                        channel.pipeline().addLast(new HeartBeatHandler(config.getHeartBeatTime()));
                        // 业务处理器
                        channel.pipeline().addLast(new NettyServerHandler());
                    }
                });
    }

    // 启动服务
    public void start() {
        this.server.bind(this.config.getTcpPort());
    }

}
