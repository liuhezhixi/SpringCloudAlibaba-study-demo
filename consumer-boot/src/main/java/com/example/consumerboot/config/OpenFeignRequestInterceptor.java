package com.example.consumerboot.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class OpenFeignRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        // 从header获取X-token
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes attr = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest request = attr.getRequest();
        String token = request.getHeader("x-auth-token");//网关传过来的 名为：""x-auth-token"的token

        if (StringUtils.hasText(token)) {
            //在openFeign的RequestTemplate的header中，设置进名为"X-AUTH-TOKEN"的token，下游服务通过"X-AUTH-TOKEN"即可从header中获得token
            template.header("X-AUTH-TOKEN", token);
        }
    }

}
