package com.example.nacosboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class NacosBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosBootApplication.class, args);
    }

}
