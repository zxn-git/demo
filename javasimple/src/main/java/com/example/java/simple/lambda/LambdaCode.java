package com.example.java.simple.lambda;

import com.example.java.simple.pojo.User;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

/**
 * @description:
 * @author: xiaonan.zhang
 * @create: 2018-12-24 10:12
 **/
@Slf4j
public class LambdaCode {


    public static void main(String... str) {
        LambdaCode.isNull();

    }

    /**
     * @ Description: 判断对象是否是null
     * @ Author: xiaonan.zhang
     * @ Date: 上午10:13 2018/12/24
     */
    public static void isNull() {
        String aaa = "aaa";
        String bbb = null;
        Optional<String> opt = Optional.ofNullable(aaa);
        //判断值是否存在
        if (opt.isPresent()) {
            log.info("aaa是空");
        }

        //只有在非空时执行
        opt.ifPresent(u -> log.info("对象存在：{}", opt.get()));

        //如果存在值，则返回该值，如果不存在值，则返回它收到的参数
        // bbb不是null，aaa也会执行
        String s = Optional.ofNullable(bbb).orElse(aaa);
        log.info("对象不存在，返回默认值：{}", s);

        //如果存在返回值，不存在执行Supplier 函数接口
        //bbb不是null，orelseget不会执行
        String s1 = Optional.ofNullable(bbb).orElseGet(() -> aaa);
        log.info("对象不存在，返回默认值：{}", s1);

        //Map() 将 Function 参数作为值，然后返回 Optional 中经过封装的结果。这将使我们可以在后续附加一些操作，比如此处的 orElse()
        String ccc = "ccc";
        String s2 = Optional.ofNullable(aaa)
                .map(o -> {
                    String aaaa = null;
                    return aaaa;
                })
                .orElseGet(() -> ccc);
        log.info("对象不存在，返回默认值：{}", s2);
    }
}
