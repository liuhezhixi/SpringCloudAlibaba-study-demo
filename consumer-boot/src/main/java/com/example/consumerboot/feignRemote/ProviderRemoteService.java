package com.example.consumerboot.feignRemote;


import com.example.consumerboot.fallback.ProviderRemoteServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name = "provider-boot", fallback = ProviderRemoteServiceFallback.class)
public interface ProviderRemoteService {

    @GetMapping("/database/selectInMysql")
    String selectInMysql(@RequestParam(value = "userid") Integer userId);
}
