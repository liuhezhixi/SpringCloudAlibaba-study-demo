package com.example.providerboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@EnableFeignClients // feign的客户端
@SpringBootApplication
public class ProviderBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderBootApplication.class, args);
    }

}
