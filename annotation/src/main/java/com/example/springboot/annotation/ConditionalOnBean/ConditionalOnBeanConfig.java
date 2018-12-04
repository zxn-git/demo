package com.example.springboot.annotation.ConditionalOnBean;

import com.example.springboot.annotation.ConditionalOnBean.ready.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: demo
 * @description: 测试注解 {@link ConditionalOnBean}
 * @author: xiaonan.zhang
 * @create: 2018-12-04 17:08
 **/
@Configuration
public class ConditionalOnBeanConfig {

    /**
     * @ Description: 会生成一个name为getClassAf对象在beanfactory中
     * @ Author: xiaonan.zhang
     * @ Date: 下午5:20 2018/12/4
     */
    @Bean
    public ClassA getClassAf() {
        return new ClassA();
    }

    /**
     * @ Description:
     * 仅在beanFactory存在一个名称叫做getClassAf的bean时，当前方法初始化一个名字为getClassB的bean
     * @ Author: xiaonan.zhang
     * @ Date: 下午5:21 2018/12/4
     */
    @Bean
    @ConditionalOnBean(name = "getClassAf")
    public ClassB getClassB() {
        return new ClassB();
    }


    /**
     * @ Description:  创建一个类型为 SimpleInt 的 bean ，其实现类使用 ASimpleInt
     * @ Author: xiaonan.zhang
     * @ Date: 下午5:22 2018/12/4
     */
    @Bean
    public SimpleInt getSimpleIntA() {
        return new ASimpleInt();
    }


    /**
     * @ Description: 仅在 beanFactory 中存在一个类型为 SimpleInt 的 bean 时才初始化一个类型同样 为 SimpleInt
     * 的 bean ，bean 名称为 getSimpleIntB
     * @ Author: xiaonan.zhang
     * @ Date: 下午5:22 2018/12/4
     */
    @Bean
    @ConditionalOnBean
    public SimpleInt getSimpleIntB() {
        return new BSimpleInt();
    }
}
