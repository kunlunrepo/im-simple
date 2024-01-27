package com.lld.tcp.config;

import lombok.Data;

/**
 * description :
 *
 * @author kunlunrepo
 * date :  2024-01-26 15:35
 */
@Data
public class BootStrapConfig {

    // tcp配置
    private TcpConfig lim;

    @Data
    public static class TcpConfig {
        // tcp端口
        private Integer tcpPort;
        // websocket端口
        private Integer webSocketPort;
        // 是否启用websocket
        private boolean enableWebsocket;
        // 主线程数
        private Integer bossThreadSize;
        // 工作线程数
        private Integer workThreadSize;
        // 心跳超时时间 单位：毫秒
        private Long heartBeatTime;
        // 登录模式
        private Integer loginModel;
        // 登录地址
        private String loginUrl;
        //
        private Integer brokerId;

    }

}
