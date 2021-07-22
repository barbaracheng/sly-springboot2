package com.sly.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * TODO:@MapperScan("com.sly.springboot.mapper")
 * 扫描指定包下面的所有接口，在编译之后都会生成对应的实现类
 * @author leyuan
 * @date 2021/7/22 12:57
 */
@SpringBootApplication
@MapperScan("com.sly.springboot.mapper")
public class CustomerSpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(CustomerSpringBootApplication.class,args);
    }
}
