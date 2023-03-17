package com.example.consumerrocketmqboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ConsumerRocketmqBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerRocketmqBootApplication.class, args);
    }

}
