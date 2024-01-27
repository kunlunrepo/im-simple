package com.lld.codec;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

/**
 * description : websocket编码器
 *
 * @author kunlunrepo
 * date :  2024-01-27 10:14
 */
public class WebSocketMessageEncoder extends MessageToMessageEncoder {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object o, List list) throws Exception {

    }
}
