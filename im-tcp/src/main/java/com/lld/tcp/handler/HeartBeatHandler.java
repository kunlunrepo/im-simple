package com.lld.tcp.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * description : 心跳
 *
 * @author kunlunrepo
 * date :  2024-01-27 09:31
 */
@Slf4j
public class HeartBeatHandler extends ChannelInboundHandlerAdapter {

    // 心跳超时时间
    private Long heartBeatTime;

    public HeartBeatHandler(Long heartBeatTime) {
        this.heartBeatTime = heartBeatTime;
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        // 判断是否是心跳事件
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state() == IdleState.READER_IDLE) {
                log.info("【项目运行】---读空闲");
            } else if (event.state() == IdleState.WRITER_IDLE) {
                log.info("【项目运行】---写空闲");
            } else if (event.state() == IdleState.ALL_IDLE) {
                log.info("【项目运行】---读写空闲");
                // TODO 如果超过一定时间没有收到心跳包，那么就强制用户退出
            }
        }

    }
}
