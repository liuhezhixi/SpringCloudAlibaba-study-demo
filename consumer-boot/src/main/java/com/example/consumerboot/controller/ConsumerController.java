package com.example.consumerboot.controller;

import com.alibaba.fastjson2.JSON;
import com.example.consumerboot.feignRemote.ProviderRemoteService;
import com.example.consumerboot.pojo.UserPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class ConsumerController {

    @Autowired
    private ProviderRemoteService providerRemoteService;

    /**
     * 测试Openfeign远程调用，以及服务降级(数据兜底)
     * 请求模版：
     * http://127.0.0.1:8301/user/select/88
     *
     * @param userId
     * @return
     */
    @GetMapping("/select/{userId}")
    public String selectUser(@PathVariable("userId") Integer userId) {

        String string = providerRemoteService.selectInMysql(userId);

        return string;
    }

    /**
     * 测试 Openfeign结合Ribbon
     * 1、实现指定下游服务的负载均衡(随机、轮询)
     * 2、实现"请求处理超时时间"超时，触发服务降级
     */
    @GetMapping("/testOpenfeignAndRibbonServiceDegradation/{userId}")
    public String testOpenfeignAndRibbonServiceDegradation(@PathVariable("userId") Integer userId) {
        String returnString = providerRemoteService.testOpenfeignAndRibbonServiceDegradation(userId);
        return returnString;
    }

    /**
     * 测试Openfeign认证传递
     *
     * http://127.0.0.1:8301/testOpenfeignAuthenticationTransfer
     * {
     * "userId": 100,
     * "userName": "阿标"
     * }
     *
     * @param userPojo
     * @return
     */
    @PostMapping("/testOpenfeignAuthenticationTransfer")
    public String testOpenfeignAuthenticationTransfer(@RequestBody UserPojo userPojo) {
        System.out.println("userPojo = " + JSON.toJSONString(userPojo));
        String headerToken = providerRemoteService.getHeaderToken(
                userPojo.getUserId(),
                userPojo.getUserName());

        return headerToken;
    }
}
