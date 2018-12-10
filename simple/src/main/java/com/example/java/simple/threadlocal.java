package com.example.java.simple;

/**
 * @description:
 * @author: xiaonan.zhang
 * @create: 2018-12-06 14:08
 **/
public class threadlocal {
    public static void getValue() {
        ThreadLocal<String> stringThreadLocal = new ThreadLocal<>();
        String s = stringThreadLocal.get();
        System.out.println(s);
    }

    public static void main(String... ss){
        ThreadLocal<String> stringThreadLocal = new ThreadLocal<>();
        stringThreadLocal.set("线程插入");
        getValue();


    }


}
