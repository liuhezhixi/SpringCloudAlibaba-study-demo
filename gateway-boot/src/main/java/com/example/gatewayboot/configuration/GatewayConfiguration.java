package com.example.gatewayboot.configuration;

import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.BlockRequestHandler;
import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.GatewayCallbackManager;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRule;
import com.alibaba.fastjson2.JSON;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;


/**
 * 限流规则配置类
 * todo 待写：具体的限流降级处理
 */
@Configuration
public class GatewayConfiguration {


    // 初始化限流或者降级的回调函数
    // @PostConstruct的作用是在当前类的构造函数执行完之后就立即执行标注了‘@PostConstruct’的方法
    @PostConstruct
    public void doInit(){
        //设置触发了Sentinel的限流后的服务降级
        sentinelBlockException();
    }

    //设置限流或降级的回调函数
    public void sentinelBlockException(){

        BlockRequestHandler blockRequestHandler = new BlockRequestHandler() {
            // 被限流或者降级处理的方法
            @Override
            public Mono<ServerResponse> handleRequest(ServerWebExchange serverWebExchange, Throwable throwable) {

                Map<String, String> result = new HashMap<>();
                //这里放入了code=429
                result.put("code", String.valueOf(HttpStatus.TOO_MANY_REQUESTS.value()));
                //这里设置了返回message="Too Many Requests"
                result.put("message", HttpStatus.TOO_MANY_REQUESTS.getReasonPhrase());
                //这里是diy内容了，可以自定义多个Key&Value
                result.put("requestMapping", serverWebExchange.getRequest().getURI().toString());
                result.put("Throwable总信息", JSON.toJSONString(throwable));
                //ParamFlowException这个类型确定方法：debug的时候查看throwable的实际子类是ParamFlowException
                ParamFlowException paramFlowException = (ParamFlowException) throwable;
                result.put("发送错误的route的id是", paramFlowException.getResourceName());


                return ServerResponse.status(HttpStatus.TOO_MANY_REQUESTS)
                        .contentType(MediaType.APPLICATION_JSON)
                        //.body(BodyInserters.fromValue(result));
                        .body(BodyInserters.fromObject(result));

                //return ServerResponse.status(202).syncBody("系统繁忙请稍后");
            }
        };

        //设置限流或降级的回调函数
        GatewayCallbackManager.setBlockHandler(blockRequestHandler);
    }

}
