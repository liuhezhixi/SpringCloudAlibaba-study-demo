package com.example.gatewayboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class GatewayBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayBootApplication.class, args);
    }

}
