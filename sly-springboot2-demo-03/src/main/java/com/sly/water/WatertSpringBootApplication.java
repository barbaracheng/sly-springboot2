package com.sly.water;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * TODO: SpringBoot启动类
 *
 * @author leyuan
 * @date 2021/7/22 15:07
 */
@SpringBootApplication
@MapperScan("com.sly.water.mapper")
public class WatertSpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(WatertSpringBootApplication.class,args);
    }
}
