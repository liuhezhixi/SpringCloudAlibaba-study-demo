package com.example.consumerboot.fallback;

import com.example.consumerboot.feignRemote.ProviderRemoteService;
import org.springframework.stereotype.Component;

@Component
public class ProviderRemoteServiceFallback implements ProviderRemoteService {
    @Override
    public String selectInMysql(Integer userId) {
        return "我是兜底数据";
    }
}
