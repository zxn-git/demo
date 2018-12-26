package com.example.java.simple.filepath;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @description:
 * @author: xiaonan.zhang
 * @create: 2018-12-12 19:00
 **/
public class PathUtils {


    /**
     * @ Description: 文件操作
     */
    public static void main(String... strings) throws IOException {
        Path path = Paths.get("/opt/algorithm/yitu");
        //判断文件是否存在
        if (Files.notExists(path)) {
            //创建文件
            Path file = Files.createFile(path);
        }

        //遍历文件夹下文件
        Files.list(path).forEach(file -> {
            try {
                byte[] bytes = Files.readAllBytes(file);
            } catch (IOException e) {
                e.printStackTrace();
            }

        });


    }
}
