package com.example.providerboot.handlerException;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.fastjson.JSON;
import com.example.providerboot.fallback.ProviderControllerFallback;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class ProviderControllerHandlerException {

    @Value("${server.port}")
    private static String port;

    public static String sentinelServiceDegradationHandlerException(Integer userId,
                                                                    BlockException blockException){
        System.out.println("Provider-boot:" + port+"，的机器，被触发sentinel的blockException = sentinelServiceDegradationHandlerException()");
        return "在Provider-boot:" + port + "的机器上，触发sentinel的blockHandler！ blockException = "+JSON.toJSONString(blockException);
    }


}
