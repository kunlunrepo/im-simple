package com.lld.codec.proto;

import lombok.Data;
import lombok.ToString;

/**
 * description : 消息
 *
 * @author kunlunrepo
 * date :  2024-01-27 14:31
 */
@Data
@ToString
public class Message {

    private MessageHeader messageHeader;

    private Object messagePack;

}
