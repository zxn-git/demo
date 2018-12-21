package com.example.java.simple.lambda;

/**
 * @description:
 * @author: xiaonan.zhang
 * @create: 2018-12-20 18:26
 **/

import com.example.java.simple.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.ExceptionUtils;
import org.springframework.util.SerializationUtils;

import java.beans.Introspector;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.lang.invoke.SerializedLambda;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
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
    private static final Map<String, Map<String, String>> LAMBDA_CACHE = new ConcurrentHashMap<>();

    /**
     * SerializedLambda 反序列化缓存
     */
    private static final Map<Class, WeakReference<SerializedLambda>> FUNC_CACHE = new ConcurrentHashMap<>();

    /**
     * <p>
     * 解析 lambda 表达式
     * </p>
     *
     * @param func 需要解析的 lambda 对象
     * @param <T>  类型，被调用的 Function 对象的目标类型
     * @return 返回解析后的结果
     */
    public static <T> String resolve(SFunction<T, ?> func) {
        try {
            //不是很懂这个方法是干什么用的
            Method method = func.getClass().getDeclaredMethod("writeReplace");
            method.setAccessible(Boolean.TRUE);
            SerializedLambda serializedLambda = (SerializedLambda) method.invoke(func);
            String getter = serializedLambda.getImplMethodName();
            String fieldName = Introspector.decapitalize(getter.replace("get", ""));
            return fieldName;
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }


//    /**
//     * 通过反序列化转换 lambda 表达式，该方法只能序列化 lambda 表达式，不能序列化接口实现或者正常非 lambda 写法的对象
//     *
//     * @param lambda lambda对象
//     * @return 返回解析后的 SerializedLambda
//     */
//    public static SerializedLambda resolve(SFunction lambda) {
//        if (!lambda.getClass().isSynthetic()) {
//            throw ExceptionUtils.mpe("该方法仅能传入 lambda 表达式产生的合成类");
//        }
//        try (ObjectInputStream objIn = new ObjectInputStream(new ByteArrayInputStream(SerializationUtils.serialize(lambda))) {
//            @Override
//            protected Class<?> resolveClass(ObjectStreamClass objectStreamClass) throws IOException, ClassNotFoundException {
//                Class<?> clazz = super.resolveClass(objectStreamClass);
//                return clazz == java.lang.invoke.SerializedLambda.class ? SerializedLambda.class : clazz;
//            }
//        }) {
//            return (SerializedLambda) objIn.readObject();
//        } catch (ClassNotFoundException | IOException e) {
//            throw ExceptionUtils.mpe("This is impossible to happen", e);
//        }
//    }

    public static void main(String... star) {
        String resolve = resolve(User::getName);
        log.info("输出字段名：{}", resolve);
    }

    /**
     * 保存缓存信息
     *
     * @param className 类名
     * @param property  属性
     * @param sqlSelect 字段搜索
     */
    private static void saveCache(String className, String property, String sqlSelect) {
        Map<String, String> cacheMap = LAMBDA_CACHE.getOrDefault(className, new HashMap<>());
        cacheMap.put(property, sqlSelect);
        LAMBDA_CACHE.put(className, cacheMap);
    }

    /**
     * <p>
     * 获取实体对应字段 MAP
     * </p>
     *
     * @param entityClassName 实体类名
     * @return 缓存 map
     */
    public static Map<String, String> getColumnMap(String entityClassName) {
        return LAMBDA_CACHE.getOrDefault(entityClassName, Collections.emptyMap());
    }
}