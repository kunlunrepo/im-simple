package com.lld.codec;

import com.alibaba.fastjson.JSONObject;
import com.lld.codec.proto.Message;
import com.lld.codec.proto.MessageHeader;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.UUID;

/**
 * description : 消息解码类
 *
 * @author kunlunrepo
 * date :  2024-01-27 09:08
 */
@Slf4j
public class MessageDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        // 使用uuid生成一个不带-的batchId
        String batchId = UUID.randomUUID().toString().replace("-", "");
        log.info("*******************************[消息解码器]---开始{}*******************************", batchId);
        log.info("[消息解码器]---{} 收到消息", batchId);
        /**
         * 消息: 请求头+imei号+请求体
         * 请求头:
         *   指令 4位
         *   版本
         *   clientType 4位
         *   消息解析类型 4位
         *   appId 4位
         *   imei长度 4位
         *   bodylen
         * */
        // 如果消息长度小于28, 则不解析
        if (in.readableBytes() < 28) {
            log.error("[消息解码器]---{} 消息长度不够28", batchId);
            return;
        }

        // 根据协议转换成消息对象
        // 指令
        int command = in.readInt();
        // 版本
        int version = in.readInt();
        // clientType
        int clientType = in.readInt();
        // 解析类型
        int messageType = in.readInt();
        // appId
        int appId = in.readInt();
        // imei长度
        int imeiLength = in.readInt();
        // bodyLen长大衣
        int bodyLen = in.readInt();

        // 解决粘包问题
        if (in.readableBytes() < bodyLen + imeiLength) {
            log.error("[消息解码器]---{} 发生粘包", batchId);
            // 重置 回到上一次读取位置
            in.resetReaderIndex();
            return;
        }

        // 读取imei
        byte[] imeiData = new byte[imeiLength];
        in.readBytes(imeiData);
        String imei = new String(imeiData); // 转换成字符串

        // 读取body数据
        byte[] bodyData = new byte[bodyLen];
        in.readBytes(bodyData);

        // 创建消息
        Message message = new Message();

        // 构建消息头 并用set方法设置消息
        MessageHeader header = new MessageHeader();
        header.setCommand(command);
        header.setVersion(version);
        header.setClientType(clientType);
        header.setMessageType(messageType);
        header.setAppId(appId);
        header.setLength(bodyLen);
        header.setImeiLength(imeiLength);
        header.setImei(imei);

        log.info("[消息解码器]---{} 消息头 {}", batchId, header);
        message.setMessageHeader(header);

        // 解析消息体
        if(messageType == 0x0) {
            String body = new String(bodyData);
            JSONObject parse = JSONObject.parseObject(body);
            log.info("[消息解码器]---{} 消息体 {}", batchId, parse);
            message.setMessagePack(parse);
        }

        // 标志读取位置
        in.markReaderIndex();

        // 传递消息
        log.info("[消息解码器]---{} 发送消息", batchId);
        out.add(message);
        log.info("*******************************[消息解码器]---结束{}*******************************", batchId);
    }
}
