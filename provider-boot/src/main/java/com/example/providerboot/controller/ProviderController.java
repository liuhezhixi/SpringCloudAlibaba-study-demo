package com.example.providerboot.controller;

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

        return "在Provider-boot:" + port + "的机器上，经过Mysql的查找，已经成功找到userId = " + userId + "的数据。";
    }

    @PostMapping("/getHeaderToken")
    public String getHeaderToken(HttpServletRequest req,
                                 @RequestParam(value = "userid") Integer newNameUserId,
                                 @RequestParam(value = "username") String newNameUserName) {
        String token = req.getHeader("X-AUTH-TOKEN");
        return "在Provider-boot:" + port + "的机器上，成功获得认证传递，token = " + token + "，而且已经成功找到userId = " + newNameUserId + " ，userName = " + newNameUserName + "的数据。";
    }
}
