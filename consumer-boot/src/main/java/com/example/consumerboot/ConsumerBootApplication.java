package com.example.consumerboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients // feign的客户端
@EnableDiscoveryClient
@SpringBootApplication
public class ConsumerBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerBootApplication.class, args);
    }

}
