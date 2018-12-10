## 官网地址
https://github.com/spring-cloud/spring-cloud-sleuth


## pom文件
```$xslt
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-sleuth</artifactId>
			<exclusions>
				<!--禁用监控-->
				<exclusion>
					<artifactId>spring-boot-starter-actuator</artifactId>
					<groupId>org.springframework.boot</groupId>
				</exclusion>
			</exclusions>
		</dependency>
```
如上，排除了监控功能，觉得监控功能意义不大

## 深入一个类分析

```$xslt

TraceFeignClientAutoConfiguration.java
```

## 追踪原理
> Spring Cloud Sleuth可以追踪10种类型的组件，async、Hystrix，messaging，websocket，rxjava，scheduling，web（Spring MVC Controller，Servlet），webclient（Spring RestTemplate）、Feign、Zuul。下面是常用的八种类型。

#### Scheduled
原理是AOP处理Scheduled注解 
TraceSchedulingAspect可以带出，只要是在IOC容器中的Bean带有@Scheduled注解的方法的调用都会被sleuth处理。

#### Messaging
原理是基于spring messaging的ChannelInterceptor。 
TraceChannelInterceptor/IntegrationTraceChannelInterceptor 
MessagingSpanTextMapExtractor和MessagingSpanTextMapInjector

#### Hystrix
原理是使用HystrixPlugins添加trace相关的plugin，自定义了一个HystrixConcurrencyStrategy的实现SleuthHystrixConcurrencyStrategy 
具体参考TraceCommand和SleuthHystrixConcurrencyStrategy

#### Feign
原理是实现了两个Feign Client实例，一个不带Ribbon TraceFeignClient、一个带Ribbon，TraceLoadBalancerFeignClient 
TraceFeignAspect AOP里面的逻辑是，有地方想获取Client实例，就拦截返回自己封装的Client。

#### Async
@Async注解和ThreadPoolTaskExecutor下面的类 
具体参看TraceAsyncAspect

#### RestTempate
原理是spring client的Interceptor机制。具体参看TraceRestTemplateInterceptor。

#### Zuul
原理是zuul的Filter机制，ZuulFilter 
实现了三个TracePreZuulFilter、TracePostZuulFilter两个Filter。

