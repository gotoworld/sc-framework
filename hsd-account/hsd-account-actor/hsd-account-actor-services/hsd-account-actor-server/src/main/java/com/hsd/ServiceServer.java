package com.hsd;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@SpringCloudApplication
@EnableFeignClients(basePackages = {"com.hsd"})

@EnableCircuitBreaker
@EnableHystrix
@EnableHystrixDashboard

@RestController
public class ServiceServer {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(ServiceServer.class, args);
    }

    @Value("${server.port}")
    String port;

    @RequestMapping(value = "/")
    @HystrixCommand
    @ResponseBody
    public ResponseEntity<String> hello() {
        return new ResponseEntity<String>("hello"+port, HttpStatus.OK);
    }

}
