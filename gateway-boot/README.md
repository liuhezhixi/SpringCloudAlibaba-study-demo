# 工程简介
Gateway网关服务

如果开启IDEA允许SpringBoot项目重复运行功能
port:
8401 ~ 8499
# 延伸阅读
实现效果：
gateway-boot项目成功注册进入Nacos注册中心

通过gateway网关的指定断言，断言成功后，去访问指定在注册中心，注册成功的服务
gateway结合Sentinel，实现dashboard控制台支持网关流控规则管理
gateway结合Sentinel，实现限流控制，触发blockException

成功使用Sentinel结合nacos，实现普通微服务的流控规则的持久化存储
成功使用Sentinel结合nacos，实现普通微服务的降级规则的持久化存储