package com.example.providerboot.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.fastjson.JSON;
import com.example.providerboot.fallback.ProviderControllerFallback;
import com.example.providerboot.handlerException.ProviderControllerHandlerException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/database")
public class ProviderController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/selectInMysql")
    public String selectInMysql(@RequestParam(value = "userid") Integer userId) {
        System.out.println("Provider-boot:" + port+"，的机器被请求");
        return "在Provider-boot:" + port + "的机器上，经过Mysql的查找，已经成功找到userId = " + userId + "的数据。";
    }

    @PostMapping("/getHeaderToken")
    public String getHeaderToken(HttpServletRequest req,
                                 @RequestParam(value = "userid") Integer newNameUserId,
                                 @RequestParam(value = "username") String newNameUserName) {
        String token = req.getHeader("X-AUTH-TOKEN");
        return "在Provider-boot:" + port + "的机器上，成功获得认证传递，token = " + token + "，而且已经成功找到userId = " + newNameUserId + " ，userName = " + newNameUserName + "的数据。";
    }

    @GetMapping("/testOpenfeignAndRibbonServiceDegradation")
    public String testOpenfeignAndRibbonServiceDegradation(@RequestParam(value = "userid") Integer userId) {
        System.out.println("Provider-boot:" + port+"，的机器被请求");

        //测试上游服务Openfeign+Ribbon的"请求处理超时时间"的时候，当睡眠时间 > 上游服务设置的ReadTimeout时间，看是否会发生降级或者熔断
        try {
            //Thread.sleep(2500);
            Thread.sleep(3500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "在Provider-boot:" + port + "的机器上，通过testOpenfeignAndRibbonServiceDegradation()，已经成功找到userId = " + userId + "的数据。";
    }

    @GetMapping("/sentinelServiceDegradation")
    @SentinelResource(
            value = "sentinelServiceDegradation",
            blockHandler = "sentinelServiceDegradationHandlerException",
            blockHandlerClass = ProviderControllerHandlerException.class,
            fallback = "sentinelServiceDegradationFallback",
            fallbackClass = ProviderControllerFallback.class
    )
    public String sentinelServiceDegradation(@RequestParam(value = "userid") Integer userId) {
        System.out.println("Provider-boot:" + port+"，的机器被请求");

        //如果需要触发sentinel的fallback，需要打开下面的算数异常
        int x = 1/0;

        return "在Provider-boot:" + port + "的机器上，通过sentinelServiceDegradation()，已经成功找到userId = " + userId + "的数据。";
    }

}
