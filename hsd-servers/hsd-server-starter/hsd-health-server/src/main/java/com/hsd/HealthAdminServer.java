package com.hsd;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import de.codecentric.boot.admin.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringCloudApplication
@Configuration
@EnableAdminServer

@EnableTurbine
@EnableCircuitBreaker
@EnableHystrix
@EnableHystrixDashboard

@RefreshScope
@RestController
public class HealthAdminServer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application;
    }
    public static void main(String[] args) {
        SpringApplication.run(HealthAdminServer.class, args);
    }

    @RequestMapping(value = "/hello")
    @HystrixCommand
    public ResponseEntity<String> hello() {
        return new ResponseEntity<String>("应用监控中心", HttpStatus.OK);
    }
}