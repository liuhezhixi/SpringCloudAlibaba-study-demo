package com.example.producerrocketmqboot;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

@SpringBootTest
class ProducerRocketmqBootApplicationTests {

    @Test
    void contextLoads() {
    }


    /**
     * RocketMQ消费端 链接测试
     * @throws MQClientException
     * @throws InterruptedException
     */
    @Test
    public void RocketMqConsumerConnectTest() throws MQClientException, InterruptedException {
        DefaultMQPushConsumer consumer = new
                DefaultMQPushConsumer("niwei_consumer_group");
        consumer.setNamesrvAddr("172.16.61.133:9876");
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.subscribe("lagouMessage", "*");
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext context) {
                if (list != null) {
                    for (MessageExt ext : list) {
                        try {
                            System.out.println(new Date() + new
                                    String(ext.getBody(), "UTF-8"));
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();
        System.out.println("消息消费者已启动");

        Thread.sleep(1000000000);

    }

    /**
     * RocketMQ生产端 链接测试
     * @throws MQClientException
     * @throws UnsupportedEncodingException
     * @throws MQBrokerException
     * @throws RemotingException
     * @throws InterruptedException
     */
    @Test
    public void RocketMqProductConnectTest() throws MQClientException, UnsupportedEncodingException, MQBrokerException, RemotingException, InterruptedException {
        DefaultMQProducer producer = new DefaultMQProducer("niwei_producer_group");
        producer.setNamesrvAddr("172.16.61.133:9876");
        producer.setSendMsgTimeout(100000);
        producer.start();
        for (int i = 0; i < 100; i++) {
            Message msg = new Message(
                    "lagouMessage" /* 消息主题名 */,
                    "TagA" /* 消息标签 */,
                    ("Hello Java demo RocketMQ " +
                            i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* 消息内容 */
            );
            SendResult sendResult = producer.send(msg);
            System.out.printf("%s%n", sendResult);
        }
        producer.shutdown();
    }
}
