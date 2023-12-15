package com.lld.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class ServiceApp {

    public static void main(String[] args) {
        log.info("【IM通信系统】启动中...");
        SpringApplication.run(ServiceApp.class);
        log.info("【IM通信系统】完成");
    }

}