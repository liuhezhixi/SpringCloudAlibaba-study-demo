package com.example.consumerboot.fallback;

import com.example.consumerboot.feignRemote.ProviderRemoteService;
import org.springframework.stereotype.Component;

@Component
public class ProviderRemoteServiceFallback implements ProviderRemoteService {
    @Override
    public String selectInMysql(Integer userId) {
        return "我是'selectInMysql()'的兜底数据";
    }

    @Override
    public String getHeaderToken(Integer userId, String userName) {
        return "我是'getHeaderToken()'的兜底数据";
    }
}
