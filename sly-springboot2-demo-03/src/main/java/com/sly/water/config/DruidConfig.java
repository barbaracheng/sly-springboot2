package com.sly.water.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * TODO: Druid数据源监控
 * 被@Configuration注解修饰的类可以替换xml配置文件
 * *@Configuration用于构建Spring，启动Spring容器
 * @author leyuan
 * @date 2021/7/29 9:55
 */
@Configuration
public class DruidConfig {

    /**
     * Druid数据源注入到Spring容器
     *
     * *@ConfigurationProperties(prefix = "spring.datasource")表示获取yml配置文件
     * 前缀为spring.datasource的所有属性值
     * 被@Bean注解的方法被AnnotationConfigWebApplicationContext类扫描，将方法
     * 返回的对象注入到Spring容器
     * @return 数据源对象
     */
    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSource createDataSource() {
        return new DruidDataSource();
    }


    /**
     * 注册Druid后台监控
     * SpringBoot 内置了Servlet容器，所以没有web.xml
     */
    @Bean
    public ServletRegistrationBean createDruidServlet() {
        ServletRegistrationBean<StatViewServlet> statBean =
                new ServletRegistrationBean<>(
                        new StatViewServlet(),"/druid/*");

        // 配置登录Druid后台监控系统的初始账号和密码
        Map<String, String> initParams = new ConcurrentHashMap<>();
        // 登录的key都是固定的
        initParams.put("loginUsername","leyuan");
        initParams.put("loginPassword","1234");
        // 允许谁访问
        initParams.put("allow","");
        // 设置初始化参数
        statBean.setInitParameters(initParams);

        return statBean;
    }



}
