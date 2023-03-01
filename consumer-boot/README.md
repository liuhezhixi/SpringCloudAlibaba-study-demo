# 工程简介
消费者（调用者）

如果开启IDEA允许SpringBoot项目重复运行功能
port:
8301 ~ 8399
# 延伸阅读
实现效果：
1、consumer-boot项目成功注册进入Nacos注册中心
2、consumer-boot项目成功使用OpenFeign的远程调用，在nacos成功注册的provider-boot项目的指定接口
3、consumer-boot项目成功使用OpenFeign的fallback兜底数据功能