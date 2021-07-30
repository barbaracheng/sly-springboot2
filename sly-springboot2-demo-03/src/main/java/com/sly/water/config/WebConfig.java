package com.sly.water.config;

import com.sly.water.controller.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO:
 *
 * @author leyuan
 * @date 2021/7/30 13:47
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration ir = registry.addInterceptor(loginInterceptor);
        // 拦截路径
        ir.addPathPatterns("/*");
        ir.addPathPatterns("/cust/*");
        ir.addPathPatterns("/worker/*");
        ir.addPathPatterns("/history/*");
        ir.addPathPatterns("/salary/*");
        // 不拦截路径
        List<String> irs = new ArrayList<>();
        irs.add("/login");
        irs.add("/");
        ir.excludePathPatterns(irs);
    }
}
