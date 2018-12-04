package com.demo.cloud.sleuth.pojo;

/**
 * @program: demo
 * @description:
 * @author: xiaonan.zhang
 * @create: 2018-12-02 15:01
 **/
public enum ResultEnum {
    OK("成功", 0);

    ResultEnum(String message, int code) {
        this.code = code;
        this.message = message;
    }

    private String message;
    private int code;

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
}
