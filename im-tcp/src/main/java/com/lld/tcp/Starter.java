package com.lld.tcp;

import com.lld.tcp.config.BootStrapConfig;
import com.lld.tcp.server.LimServer;
import com.lld.tcp.server.LimWebSocketServer;
import lombok.extern.slf4j.Slf4j;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * description : TCP网关
 *
 * @author kunlunrepo
 * date :  2024-01-26 14:14
 */
@Slf4j
public class Starter {

    // 入口
    public static void main(String[] args) {
        // 如果有参数,则启动服务
        if (args.length > 0) {
            // 启动服务
            start(args[0]);
        } else {
            log.error("【项目启动异常】---未传启动参数");
        }
    }

    // 启动方法
    private static void start(String path) {
        try {
            // 1.读取配置文件 (绝对路径)
            InputStream in = new FileInputStream(path);
            // 文件流中读取配置信息
            BootStrapConfig config = new Yaml().loadAs(in, BootStrapConfig.class);
            log.info("【项目启动】---配置 {}", config);

            // 2.启动服务
            new LimServer(config.getLim()).start();
            log.info("【项目启动】---启动服务");

            // 3.启动websocket服务
            new LimWebSocketServer(config.getLim()).start();
            log.info("【项目启动】---启动websocket服务");

            // 4.初始化redis

            // 5.初始化mq

            // 6.初始化msg应用

            // 7.初始化zookeeper
        } catch (FileNotFoundException e) {
            log.error("【项目启动异常】---配置文件不存在", e);
        } catch (Exception e) {
            log.error("【项目启动异常】---运行异常", e);
        }
    }
}
