package com.hsd;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


//@EnableHystrix
@EnableCircuitBreaker
@EnableAutoConfiguration
@SpringBootApplication
@EnableEurekaClient
@RefreshScope
public class ServiceServer {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(ServiceServer.class, args);
    }
}
