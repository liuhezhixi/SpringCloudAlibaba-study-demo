# 工程简介
生产者（被调用者）

如果开启IDEA允许SpringBoot项目重复运行功能
port:
8201 ~ 8299
# 延伸阅读
实现效果：
1、provider-boot项目成功注册进入Nacos注册中心
2、provider-boot项目成功接收OpenFeign的远程调用，接收了在nacos成功注册的consumer-boot项目的指定远程调用
3、实现sentinel的流控规则，来触发sentinel的blockException
4、通过下游服务出现Exception异常来，来触发sentinel的fallback