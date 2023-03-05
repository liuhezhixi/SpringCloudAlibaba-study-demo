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
