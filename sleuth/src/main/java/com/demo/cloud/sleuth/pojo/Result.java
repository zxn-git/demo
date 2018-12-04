package com.demo.cloud.sleuth.pojo;

import lombok.Data;

/**
 * @program: demo
 * @description:
 * @author: xiaonan.zhang
 * @create: 2018-12-02 14:49
 **/
@Data
public class Result {
    private String message;
    private int code;
    private Object result;


    public Result(ResultEnum resultEnum) {
        this.message = resultEnum.getMessage();
        this.code = resultEnum.getCode();
    }

    public static Result OK() {
        return new Result(ResultEnum.OK);
    }
}
