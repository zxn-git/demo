package com.example.java.simple.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: xiaonan.zhang
 * @create: 2018-12-19 15:02
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private String name;
    private Integer age;


    public String aaa(){
        System.out.println("sdfasdfasf");
        return "aaaa";
    }
}
