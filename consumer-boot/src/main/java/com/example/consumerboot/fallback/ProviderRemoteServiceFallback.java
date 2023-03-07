package com.example.consumerboot.fallback;

import com.example.consumerboot.feignRemote.ProviderRemoteService;
import org.springframework.stereotype.Component;

@Component
public class ProviderRemoteServiceFallback implements ProviderRemoteService {
    @Override
    public String selectInMysql(Integer userId) {
        return "我是consumer-boot的Openfeign粗发的，fallback的'selectInMysql()方法'的兜底数据";
    }

    @Override
    public String testOpenfeignAndRibbonServiceDegradation(Integer userId) {
        return "我是consumer-boot的Openfeign粗发的，fallback的'testOpenfeignAndRibbonServiceDegradation()方法'的兜底数据";
    }

    @Override
    public String getHeaderToken(Integer userId, String userName) {
        return "我是consumer-boot的Openfeign粗发的，fallback的'getHeaderToken()方法'的兜底数据";
    }

    @Override
    public String sentinelServiceDegradation(Integer userId) {
        return "我是consumer-boot的Openfeign粗发的，fallback的'sentinelServiceDegradation()方法'的兜底数据";
    }
}
