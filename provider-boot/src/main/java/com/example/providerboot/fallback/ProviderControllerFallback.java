package com.example.providerboot.fallback;

import com.alibaba.fastjson2.JSON;
import org.springframework.beans.factory.annotation.Value;

public class ProviderControllerFallback {

    @Value("${server.port}")
    private static String port;

    public static String sentinelServiceDegradationFallback(Integer userId, Throwable e) {
        //System.out.println("Provider-boot:" + port + "，的机器，被触发sentinel的fallback = sentinelServiceDegradationFallback()。");
        System.out.println("Provider-boot:" + port + "，的机器，被触发sentinel的fallback = sentinelServiceDegradationFallback()。 throwable = " + e.getMessage());
        //return "在Provider-boot:" + port + "的机器上，触发sentinel的fallback！";
        return "在Provider-boot:" + port + "的机器上，触发sentinel的fallback！throwable = " + e.getMessage();
    }
}
