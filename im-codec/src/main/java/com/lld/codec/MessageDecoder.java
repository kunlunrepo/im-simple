package com.lld.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * description : 消息解码类
 *
 * @author kunlunrepo
 * date :  2024-01-27 09:08
 */
public class MessageDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        /**
         * 消息: 请求头+imei号+请求体
         * 请求头:
         *   指令 4位
         *   clientType 4位
         *   消息解析类型 4位
         *   appId 4位
         *   imei长度 4位
         *   bodylen
         * */
        // 如果消息长度小于28, 则不解析
        if (in.readableBytes() < 28) {
            return;
        }

        // 根据协议转换成消息对象

        // 传递消息
        out.add(new Object());


    }
}
