package com.lld.codec.proto;

import lombok.Data;

/**
 * description : 消息头
 *
 * @author kunlunrepo
 * date :  2024-01-27 14:18
 */
@Data
public class MessageHeader {

    // 指令 4位
    private Integer command ;
    // 版本 4位
    private Integer version ;
    // 客户端类型 4位
    private Integer clientType ;
    // 消息类型 4位 0x0:Json,0x1:ProtoBuf,0x2:Xml
    private Integer messageType = 0x0;
    // 应用Id 4位
    private Integer appId ;
    // imei长度 4位
    private Integer imeiLength ;
    // bodyLen长大衣 4位
    private Integer length;
    // imei号
    private String imei;

    // 重写tostring方法
    @Override
    public String toString() {
        return "{" +
                "\"command\":" + command +
                ", \"version\":" + version +
                ", \"clientType\":" + clientType +
                ", \"messageType\":" + messageType +
                ", \"appId\":" + appId +
                ", \"imeiLength\":" + imeiLength +
                ", \"length\":" + length +
                ", \"imei\":\"" + imei + '\"' +
                '}';
    }
}
