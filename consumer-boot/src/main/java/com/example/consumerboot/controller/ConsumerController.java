package com.example.consumerboot.controller;

import com.example.consumerboot.feignRemote.ProviderRemoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public String selectUser(@PathVariable("userId")Integer userId){

        String string = providerRemoteService.selectInMysql(userId);

        return string;
    }
}
