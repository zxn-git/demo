#### 1.  注解 @ConditionalOnBean

参考：
```$xslt
com.example.springboot.annotation.ConditionalOnBean.ConditionalOnBeanConfig
```
---

用法：
1. 可用在cloud中，例如sleuth会监听feign类，如果feign客户端创建，
就初始化一个切面包装feign进行trice跟踪打印