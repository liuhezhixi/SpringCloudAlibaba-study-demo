package com.example.providerboot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/database")
public class ProviderController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/selectInMysql")
    public String selectInMysql(@RequestParam(value = "userid") Integer userId){

        return "在Provider-boot:"+port+"的机器上，经过Mysql的查找，已经成功找到userId = " + userId + "的数据。";
    }
}
