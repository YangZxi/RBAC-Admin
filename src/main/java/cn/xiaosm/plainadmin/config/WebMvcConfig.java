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
import cn.xiaosm.plainadmin.interceptor.MainInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 〈一句话功能简述〉
 * 〈拦截器配置文件〉
 *
 * @author Young
 * @create 2020/3/8
 * @since 1.0.0
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /**
         - /**： 匹配所有路径 /**
         - /api/*：只匹配 /api下的内容，不匹配/api/v1/*
         - /api/v1/**：匹配 /api/v1/ 下的所有路径
         */
        registry.addInterceptor(createMainInterceptor())
                .addPathPatterns("/**");
        // registry.addInterceptor(createJwtInterceptor())
        //         .addPathPatterns("/**")
        //         .excludePathPatterns("/login");

    }

    @Bean
    public MainInterceptor createMainInterceptor() {
        System.out.println("加载Main拦截器");
        return new MainInterceptor();
    }

    // @Bean
    // public JwtInterceptor createJwtInterceptor() {
    //     System.out.println("加载JWT拦截器");
    //     return new JwtInterceptor();
    // }

    // @Bean
    // public CorsInterceptor createCorsInterceptor() {
    //     System.out.println("加载Cors拦截器");
    //     return new CorsInterceptor();
    // }

}