package com.hsd;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients(basePackages = {"com.hsd"})
@EnableDiscoveryClient
public class ServiceServer {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(ServiceServer.class, args);
    }
}
