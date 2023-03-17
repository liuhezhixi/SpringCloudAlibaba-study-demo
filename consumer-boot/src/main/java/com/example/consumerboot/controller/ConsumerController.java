package com.example.consumerboot.controller;

import com.alibaba.fastjson2.JSON;
import com.example.consumerboot.feignRemote.ProducerRocketMqRemoteService;
import com.example.consumerboot.feignRemote.ProviderRemoteService;
import com.example.consumerboot.pojo.UserPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class ConsumerController {

    @Autowired
    private ProviderRemoteService providerRemoteService;

    @Autowired
    private ProducerRocketMqRemoteService producerRocketMqRemoteService;


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


    /**
     * 测试 Sentinel服务降级(数据兜底)
     * 1、实现sentinel的流控规则，来触发sentinel的blockException
     * 2、通过下游服务出现Exception异常来，来触发sentinel的fallback
     */
    @GetMapping("/testSentinelServiceDegradation/{userId}")
    public String testSentinelServiceDegradation(@PathVariable("userId") Integer userId) {
        String returnString = providerRemoteService.sentinelServiceDegradation(userId);
        return returnString;
    }


    /**
     * 测试rocketMQ的发送
     * @param userPojo
     * @return
     */
    @PostMapping("/testSendRocketmqMessage")
    public String testSendRocketmqMessage(@RequestBody UserPojo userPojo) {
        System.out.println("userPojo = " + JSON.toJSONString(userPojo));
        String headerToken = producerRocketMqRemoteService.testSendRocketmqMessage(userPojo);

        return headerToken;
    }


}
