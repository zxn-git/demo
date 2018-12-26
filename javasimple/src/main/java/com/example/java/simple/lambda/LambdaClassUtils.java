package com.example.java.simple.lambda;

/**
 * @description:
 * @author: xiaonan.zhang
 * @create: 2018-12-20 18:26
 **/

import com.example.java.simple.pojo.User;
import lombok.extern.slf4j.Slf4j;

import java.beans.Introspector;
import java.lang.invoke.SerializedLambda;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 * Lambda 解析工具类
 * 参考 mybatis-plus-boot-starter 中 Wrapper 实现
 * 通过User::getName 得到字符串"name"
 * </p>
 *
 * @author HCL
 * @since 2018-05-10
 */
@Slf4j
public final class LambdaClassUtils {
    //    https://github.com/liuyuyu/java-code-suggest/blob/master/src/main/java/io/github/liuyuyu/lambda/meta/Lambda_Meta.md
    /**
     * SerializedLambda 反序列化缓存
     */
    private static final Map<Class, WeakReference<SerializedLambda>> FUNC_CACHE = new ConcurrentHashMap<>();

    /**
     * <p>
     * 解析 lambda 表达式
     * </p>
     *
     * @return 返回解析后的 SerializedLambda
     */
    public static SerializedLambda resolveSFunction(SFunction func) {
        try {
            Method method = func.getClass().getDeclaredMethod("writeReplace");
            method.setAccessible(Boolean.TRUE);
            return (SerializedLambda) method.invoke(func);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * <p>
     * 解析 lambda 表达式
     * </p>
     *
     * @param func 需要解析的 lambda 对象
     * @param <T>  类型，被调用的 Function 对象的目标类型
     * @return 返回解析后的结果
     */
    public static <T> SerializedLambda resolve(SFunction<T, ?> func) {
        Class clazz = func.getClass();
        return Optional.ofNullable(FUNC_CACHE.get(clazz))
                .map(WeakReference::get)
                .orElseGet(() -> {
                    SerializedLambda lambda = resolveSFunction(func);
                    FUNC_CACHE.put(clazz, new WeakReference<>(lambda));
                    return lambda;
                });
    }

    protected static <T> String columnToString(SFunction<T, ?> func) {
        SerializedLambda lambda = resolve(func);
        String getter = lambda.getImplMethodName();
        String fieldName = Introspector.decapitalize(getter.replace("get", ""));
        return fieldName;
    }


    public static void main(String... star) {
        String s = columnToString(User::getName);
        log.info("字段：{}", s);
    }
}