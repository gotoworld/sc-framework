package com.hsd;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import zipkin.server.EnableZipkinServer;

@SpringCloudApplication
@EnableCircuitBreaker
@EnableZipkinServer
@RestController
public class Application {
    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class).web(true).run(args);
    }

    @RequestMapping(value = "/")
    @ResponseBody
    public ResponseEntity<String> hello() {
        return new ResponseEntity<String>("服务链路追踪", HttpStatus.OK);
    }
}