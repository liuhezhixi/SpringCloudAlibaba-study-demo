package com.example.consumerboot.config;

import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RibbonConfig {

    //设置Ribbon的全局负载均衡为 随机
    //Openfeign里面的Ribbon的全局配置也是这么设置
    @Bean
    public RandomRule randomRule() {
        return new RandomRule();
    }
}
