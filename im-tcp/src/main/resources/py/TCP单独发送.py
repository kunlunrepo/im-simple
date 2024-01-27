# 测试TCP的功能

import socket
import json
import struct
import threading
import time
import uuid

# 【连接服务器】
s=socket.socket(socket.AF_INET,socket.SOCK_STREAM)
s.connect(('127.0.0.1',9000))

# base数据
command=9888
version=1
clientType=4
messageType=0x0
appId=10000
name='lld'

# 【消息头】
# 将上诉整数转换成固定长度的字节序列
commandByte = command.to_bytes(4, 'big')
versionByte = version.to_bytes(4, 'big')
clientTypeByte = clientType.to_bytes(4, 'big')
messageTypeByte = messageType.to_bytes(4, 'big')
appIdByte = appId.to_bytes(4, 'big')
# 消息标识
imei = str(uuid.uuid1())
imeiBytes = bytes(imei, 'utf-8');
imeiLength = len(imeiBytes)
imeiLengthByte = imeiLength.to_bytes(4, 'big')

# 【消息体】
msg = {"name": name, "appId": appId, "clientType": clientType, "imei": imei}
# json转字节
jsonMsg = json.dumps(msg)
msgBody=bytes(jsonMsg, 'utf-8')
msgBodyLength = len(msgBody)
msgBodyLengthByte = msgBodyLength.to_bytes(4, 'big')

# 【发送】
# 循环发送100次
for i in range(2):
    # 消息头
    header = commandByte + versionByte + clientTypeByte + messageTypeByte + appIdByte + imeiLengthByte
    # 消息体
    body = msgBodyLengthByte + imeiBytes + msgBody
    # 发送
    s.sendall(header + body)
    print(f"第{i}发送成功")
    time.sleep(0.1)