package com.demo.cloud.sleuth.controller;

import com.demo.cloud.sleuth.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: demo
 * @description:
 * @author: xiaonan.zhang
 * @create: 2018-12-02 14:47
 **/
@RestController
@RequestMapping("/index")
@Slf4j
public class indexController {

    @GetMapping("")
    public Result index() {
        log.info("有人请求我了");
        return Result.OK();
    }

}
