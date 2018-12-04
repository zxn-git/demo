package com.example.springboot.annotation.ConditionalOnBean.ready;

/**
 * @program: demo
 * @description:
 * @author: xiaonan.zhang
 * @create: 2018-12-04 17:15
 **/
public class ASimpleInt implements SimpleInt{

    public ASimpleInt(){
        System.out.println("初始化ASimpleInt");
    }

    @Override
    public int simle() {
        return 0;
    }
}
