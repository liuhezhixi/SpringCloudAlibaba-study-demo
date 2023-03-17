package com.example.consumerrocketmqboot.mqListener;

import com.alibaba.fastjson.JSON;
import com.example.consumerrocketmqboot.pojo.UserPojo;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.annotation.SelectorType;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(topic = "${lagou.rocketmq.topic}",
        consumerGroup = "${rocketmq.consumer.group}",
        selectorType = SelectorType.TAG, //选择了tag过滤模式
        selectorExpression = "${lagou.rocketmq.tagId}" //指定tag的值
)
public class RocketMqListener implements RocketMQListener<String> {

    @Value("${server.port}")
    Integer port;

    @Override
    public void onMessage(String message) {
        if (StringUtils.isBlank(message)) {
            System.out.println("message is blank");
            return;
        }
        UserPojo userPojo = JSON.parseObject(message, UserPojo.class);
        System.out.println("RocketMQ消费者 [consumer-rocketmq-boot] port = " + port + " 成功收到 = " + JSON.toJSONString(userPojo));
    }
}
