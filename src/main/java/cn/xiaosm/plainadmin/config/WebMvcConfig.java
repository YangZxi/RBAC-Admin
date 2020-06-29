/**
 * Copyright: 2019-2020，小树苗(www.xiaosm.cn)
 * FileName: InterceptorConfig
 * Author:   Young
 * Date:     2020/3/8 11:32
 * Description: 拦截器配置文件
 * History:
 */
package cn.xiaosm.plainadmin.config;

import cn.xiaosm.plainadmin.interceptor.CorsInterceptor;
import cn.xiaosm.plainadmin.interceptor.JwtInterceptor;
import cn.xiaosm.plainadmin.interceptor.LogInterceptor;
import cn.xiaosm.plainadmin.interceptor.MainInterceptor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

/**
 * 〈一句话功能简述〉
 * 〈拦截器配置文件〉
 *
 * @author Young
 * @create 2020/3/8
 * @since 1.0.0
 */
@Log4j2
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/")
                .setViewName("forward:/index.html");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("classpath:/static/upload/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /**
         - /**： 匹配所有路径 /**
         - /api/*：只匹配 /api下的内容，不匹配/api/v1/*
         - /api/v1/**：匹配 /api/v1/ 下的所有路径
         */
        List<String> excludePath = new LinkedList<>();
        excludePath.addAll(Arrays.asList(
                "/css/**",
                "/js/**",
                "/fonts/**",
                "/img/**"
        ));
        registry.addInterceptor(createMainInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(excludePath);
        registry.addInterceptor(createLogInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(excludePath)
                .excludePathPatterns("/login", "logout");
        // registry.addInterceptor(createJwtInterceptor())
        //         .addPathPatterns("/**")
        //         .excludePathPatterns("/login");

    }

    @Bean
    public MainInterceptor createMainInterceptor() {
        log.info("加载Main拦截器");
        return new MainInterceptor();
    }

    // @Bean
    // public JwtInterceptor createJwtInterceptor() {
    //     System.out.println("加载JWT拦截器");
    //     return new JwtInterceptor();
    // }

    @Bean
    public LogInterceptor createLogInterceptor() {
        log.info("加载日志记录拦截器");
        return new LogInterceptor();
    }

    // @Bean
    // public CorsInterceptor createCorsInterceptor() {
    //     System.out.println("加载Cors拦截器");
    //     return new CorsInterceptor();
    // }

}