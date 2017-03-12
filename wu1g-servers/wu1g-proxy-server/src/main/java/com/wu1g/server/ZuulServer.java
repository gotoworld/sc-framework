package com.wu1g.server;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@SpringBootApplication
@EnableZuulProxy
@ComponentScan(basePackages = {"com.wu1g"})
public class ZuulServer {

    public static void main(String[] args) {

        SpringApplication.run(ZuulServer.class, args);
    }

}