package com.example.springboot.annotation.ConditionalOnBean.ready;

/**
 * @program: demo
 * @description:
 * @author: xiaonan.zhang
 * @create: 2018-12-04 17:15
 **/
public class BSimpleInt implements SimpleInt {

    public BSimpleInt() {
        System.out.println("BSimpleInt");
    }


    @Override
    public int simle() {
        return 0;
    }
}