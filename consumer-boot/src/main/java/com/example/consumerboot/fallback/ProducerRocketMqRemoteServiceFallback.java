package com.example.consumerboot.fallback;

import com.example.consumerboot.feignRemote.ProducerRocketMqRemoteService;
import com.example.consumerboot.pojo.UserPojo;
import org.springframework.stereotype.Component;

@Component
public class ProducerRocketMqRemoteServiceFallback implements ProducerRocketMqRemoteService {

    @Override
    public String testSendRocketmqMessage(UserPojo userPojo) {
        return "我是consumer-boot的Openfeign粗发的，fallback的'testSendRocketmqMessage()方法'的兜底数据";
    }
}
