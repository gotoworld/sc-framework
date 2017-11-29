package com.hsd;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableCircuitBreaker
@EnableDiscoveryClient
@EnableZuulProxy
@RefreshScope
@RestController
public class ApiGatewayServer {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ApiGatewayServer.class).web(true).run(args);
    }

    @RequestMapping(value = "/")
    @ResponseBody
    public ResponseEntity<String> hello() {
        return new ResponseEntity<String>("Hi! 网关访问方式=>http://${网关地址}:${网关端口}/${服务名}/", HttpStatus.OK);
    }
}