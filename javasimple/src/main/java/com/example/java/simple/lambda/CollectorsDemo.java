package com.example.java.simple.lambda;

import com.alibaba.fastjson.JSON;
import com.example.java.simple.pojo.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: xiaonan.zhang
 * @create: 2018-12-19 15:00
 **/
public class CollectorsDemo {

    /**
     * @ Description: lambda转集合
     * @ Author: xiaonan.zhang
     * @ Date: 下午3:06 2018/12/19
     */
    public static void collection() {
        ArrayList<User> users = new ArrayList<>();
        users.add(User.builder().age(3).name("diyge").build());
        users.add(User.builder().age(4).name("tin").build());

        List<Integer> collect = users.stream().map(u -> u.getAge()).collect(Collectors.toList());

        System.out.println(JSON.toJSONString(collect));
        //结果：[3,4]
    }


    public static void main(String... strings) {
        //1. 转成集合
        CollectorsDemo.collection();

    }


}
