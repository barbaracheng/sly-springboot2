package com.sly.water;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * TODO:SpringBoot入口
 * 被@SpringBootApplication注解修饰的类是SpringBoot的启动类
 * @author leyuan
 * @date 2021/7/22 10:31
 */
@SpringBootApplication
public class HelloSpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(HelloSpringBootApplication.class,args);
    }
}
