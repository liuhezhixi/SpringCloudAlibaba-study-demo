package com.example.consumerboot.feignRemote;


import com.example.consumerboot.config.OpenFeignRequestInterceptor;
import com.example.consumerboot.fallback.ProviderRemoteServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
/**
 * 这里的fallback，是Openfeign的fallback
 * 触发情况，大都是下游服务出现故障，无法调用的时候，就会调用fallback里面的兜底数据
 */
@FeignClient(name = "provider-boot",
        fallback = ProviderRemoteServiceFallback.class,
        configuration = OpenFeignRequestInterceptor.class)
public interface ProviderRemoteService {

    @GetMapping("/database/selectInMysql")
    String selectInMysql(@RequestParam(value = "userid") Integer userId);


    @GetMapping("/database/testOpenfeignAndRibbonServiceDegradation")
    String testOpenfeignAndRibbonServiceDegradation(@RequestParam(value = "userid") Integer userId);


    /**
     * @RequestParam 这个注解，标注了指定的传递变量，在Openfeign的下游，被调用接口参数也有一一对应的数值
     * 只对应@RequestParam注解中的value值，只要value值相同，入参变量名不同也可以匹配上
     */
    @PostMapping("/database/getHeaderToken")
    String getHeaderToken(@RequestParam(value = "userid") Integer userId,
                          @RequestParam(value = "username") String userName);


    @GetMapping("/database/sentinelServiceDegradation")
    String sentinelServiceDegradation(@RequestParam(value = "userid") Integer userId);
}
