package com.example.producerrocketmqboot.controller;

import com.alibaba.fastjson.JSON;
import com.example.producerrocketmqboot.pojo.UserPojo;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rocketmq")
public class RocketMqController {

    @Value("${server.port}")
    private Integer port;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Value("${lagou.rocketmq.topic}")
    private String topicName;
    @Value("${lagou.rocketmq.tagId}")
    private String tagId;

    @PostMapping("/testSendRocketmqMessage")
    public String testSendRocketmqMessage(@RequestBody UserPojo userPojo) {
        System.out.println("RocketMQ生产者  [consumer-rocketmq-boot] UserPojo = " + JSON.toJSONString(userPojo));

        String tag = topicName + ":" + tagId;



        /**
         *  // 这个习惯最好保持！！！【在JSON序列化类的时候，同时把类的信息写进去】
         *  String message = JSON.toJSONString(userPojo, new SerializerFeature[]{SerializerFeature.WriteClassName});
         *  但是这里写入的是：consumer-rocketmq-boot的UserPojo.class对象，因为consumer-rocketmq-boot是独立的，并没有和其他boot项目提炼出common-boot公共包
         *  所以这里为了RockeMQ发送后，消费者能成功解析josnString，就不进行序列化了
         *
         */
        String jsonString = JSON.toJSONString(userPojo);

        rocketMQTemplate.send(tag, MessageBuilder.withPayload(jsonString).build());


        return "在producer-rocketmq-boot:" + port + "的机器上，经过Mysql的查找，已经成功找到userId = " + userPojo.getUserId() + "的数据。";
    }
}
