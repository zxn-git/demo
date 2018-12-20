package com.example.java.simple.lambda;

import com.example.java.simple.pojo.User;

import java.util.function.*;

/**
 * @description: jdk 自带一些lambda表达式
 **/
public class JdkFunction {

    /**
     * @ Description:  条件判断
     */
    public static void predicate() {

        Predicate<User> maleStudent = u -> u.getAge() >= 20 && "male".equals(u.getName());

        User name = new User("male", 33);
        if (maleStudent.test(name)) {
            System.out.println("匹配");
        }
    }


    /**
     * @ Description: 再加工
     * @ Author: xiaonan.zhang
     * @ Date: 下午6:11 2018/12/19
     */
    public static void function(Function<User, String> femaleStyle) {




        User name = new User("male", 33);
        String apply = femaleStyle.apply(name);
        System.out.println(apply);

    }

    /**
     * @ Description: 消费者
     * @ Author: xiaonan.zhang
     * @ Date: 下午6:31 2018/12/19
     */
    public static void consumer(Consumer<User> consumer) {
        User name = new User("male", 33);
        consumer.accept(name);
    }


    /**
     * @ Description: 供应者
     * @ Author: xiaonan.zhang
     * @ Date: 下午6:31 2018/12/19
     */
    public static void supplier(Supplier<User> supplier) {

    }


    /**
     * @ Description: 求和
     * @ Author: xiaonan.zhang
     * @ Date: 下午6:31 2018/12/19
     */
    public static void binaryOperator(BinaryOperator<User> binaryOperator) {

    }

    /**
     * @ Description: 逻辑非
     * @ Author: xiaonan.zhang
     * @ Date: 下午6:31 2018/12/19
     */
    public static void unaryOperator(UnaryOperator<User> userUnaryOperator) {

    }


    public static void main(String... star) {
        // 返回boolean
        predicate();
        //传入T 返回R
        function(a -> "kaishishush:" + a.getName());
        function(User::getName);
        //对象做处理
        consumer(u -> System.out.println(u.getName()));
        consumer(User::aaa);


    }
}
