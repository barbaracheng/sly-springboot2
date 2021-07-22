package com.sly.water.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO: 定义一个HelloController类
 * 被@RestController注解修饰的类在程序启动的时候会注入到SpringBoot容器中，
 * 表示这个类充当Controller(控制器)，它里面定义的方法全部以Json字符串的形式返回给浏览器
 * @author leyuan
 * @date 2021/7/22 10:28
 */
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello Springboot.......";
    }
}
