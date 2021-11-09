# SpringCloud-Seed
> 此项目为快速启动种子项目包含了目前主流的三大解决方案.

## Spring Cloud Netflix
第一套微服务架构解决方案：Spring Boot + Spring Cloud Netflix
Spring Cloud 为开发人员提供了快速构建分布式系统中一些常见模式的工具（例如配置管理，服务发现，断路器，智能路由，微代理，控制总线）。分布式系统的协调导致了样板模式, 使用 Spring Cloud 开发人员可以快速地支持实现这些模式的服务和应用程序。他们将在任何分布式环境中运行良好，包括开发人员自己的笔记本电脑，裸机数据中心，以及 Cloud Foundry 等托管平台。
【官方新闻】项目进入维护模式
spring-cloud-netflix-archaius
spring-cloud-netflix-hystrix-contract
spring-cloud-netflix-hystrix-dashboard
spring-cloud-netflix-hystrix-stream
spring-cloud-netflix-hystrix
spring-cloud-netflix-ribbon
spring-cloud-netflix-turbine-stream
spring-cloud-netflix-turbine
spring-cloud-netflix-zuul
替代品
CURRENT	REPLACEMENT
Hystrix	Resilience4j
Hystrix Dashboard / Turbine	Micrometer + Monitoring System
Ribbon	Spring Cloud Loadbalancer
Zuul 1	Spring Cloud Gateway
Archaius 1	Spring Boot external config + Spring Cloud Config

## Apache Dubbo Zookeeper
第二套微服务架构解决方案：Spring Boot + Dubbo + Zookeeper
Apache Dubbo (incubating) 是一款高性能、轻量级的开源 Java RPC 框架。
ZooKeeper 是一种分布式协调服务，用于管理大型主机。在分布式环境中协调和管理服务是一个复杂的过程。

## Spring Cloud Alibaba （推荐）
第三套微服务架构解决方案：Spring Boot + Spring Cloud Alibaba
Spring Cloud Alibaba 致力于提供微服务开发的一站式解决方案。此项目包含开发分布式应用微服务的必需组件，方便开发者通过 Spring Cloud 编程模型轻松使用这些组件来开发分布式应用服务。

依托 Spring Cloud Alibaba，您只需要添加一些注解和少量配置，就可以将 Spring Cloud 应用接入阿里分布式应用解决方案，通过阿里中间件来迅速搭建分布式应用系统。