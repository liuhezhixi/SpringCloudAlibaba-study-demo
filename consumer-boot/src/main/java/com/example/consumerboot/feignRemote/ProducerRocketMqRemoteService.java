package com.example.consumerboot.feignRemote;

import com.example.consumerboot.fallback.ProducerRocketMqRemoteServiceFallback;
import com.example.consumerboot.pojo.UserPojo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "producer-rocketmq-boot",
        fallback = ProducerRocketMqRemoteServiceFallback.class)
@Component
public interface ProducerRocketMqRemoteService {

    @PostMapping("/rocketmq/testSendRocketmqMessage")
    String testSendRocketmqMessage(@RequestBody UserPojo userPojo);
}
