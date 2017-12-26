package com.hsd;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableConfigServer
@EnableEurekaClient
@Slf4j
public class ConfigServer {

    public static void main(String[] args) {
        log.info("ConfigServer 启动.....");
        SpringApplication.run(ConfigServer.class, args);
    }
}