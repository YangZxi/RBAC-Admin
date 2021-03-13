/**
 * Copyright: 2019-2020，小树苗(www.xiaosm.cn)
 * FileName: SecurityConfig
 * Author:   Young
 * Date:     2020/6/15 9:29
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * Young         修改时间           版本号             描述
 */
package cn.xiaosm.yadmin.config.security;

import cn.xiaosm.yadmin.annotation.AnonymousAccess;
import cn.xiaosm.yadmin.config.security.handler.AccessDeniedHandlerImpl;
import cn.xiaosm.yadmin.config.security.handler.LoginFailHandler;
import cn.xiaosm.yadmin.config.security.handler.LoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 〈一句话功能简述〉
 * 〈〉
 *
 * @author Young
 * @create 2020/6/15
 * @since 1.0.0
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("userDetailsService")
    private UserDetailsService userDetailsService;
    @Autowired
    private AccessDeniedHandlerImpl accessDeniedHandler;
    @Autowired
    private AuthenticationEntryPointImpl authenticationEntryPoint;
    @Autowired
    private LoginSuccessHandler loginSuccessHandler;
    @Autowired
    private LoginFailHandler loginFailHandler;
    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;
    @Autowired
    private CorsFilter corsFilter;
    @Autowired
    private JWTAuthenticationFilter jwtAuthenticationFilter;
    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 如果某个地址无需拦截，在此进行放行
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        // web.ignoring().antMatchers("/login");
        web.ignoring().antMatchers("/static/**");
        web.ignoring().antMatchers("/oauth/**");
        super.configure(web);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        // 配置自定义service
        builder.userDetailsService(userDetailsService)
                // 配置加密方式
                .passwordEncoder(bCryptPasswordEncoder());
        super.configure(builder);
    }

    @Override
    protected void configure(HttpSecurity security) throws Exception {
        // 搜寻匿名标记 url： @AnonymousAccess
        Map<RequestMappingInfo, HandlerMethod> handlerMethodMap = applicationContext.getBean(RequestMappingHandlerMapping.class).getHandlerMethods();
        Set<String> anonymousUrls = new HashSet<>();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> infoEntry : handlerMethodMap.entrySet()) {
            HandlerMethod handlerMethod = infoEntry.getValue();
            AnonymousAccess anonymousAccess = handlerMethod.getMethodAnnotation(AnonymousAccess.class);
            if (null != anonymousAccess) {
                anonymousUrls.addAll(infoEntry.getKey().getPatternsCondition().getPatterns());
            }
        }
        String[] staticPath = new String[]{
                "/upload/**", "/*.html", "/**/*.html",
                "/**/*.css", "/**/*.js", "/**/*.map",
                "/**/*.woff", "/**/*.ttf",
                "/**/*.png", "/**/*.jpg", "/**/*.ico",
                "/**/*.gif", "/**/*.svg"
        };

        // 禁用 CSRF 因为我不需要 session 鸭
        security.csrf().disable()
                // 不创建会话，因为此项目基于 Java Web Token 进行登录
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // 添加跨域请求（允许）过滤器
        security.addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class);
        // 添加 JWT 过滤器
        security.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        // 登录处理
        security.formLogin()
                // 因为写了自己的Controller，所以需要注释这行，防止我们自定义的不生效
                // .loginProcessingUrl("/api/login")
                // 登录成功调用
                .successHandler(loginSuccessHandler)
                // 登录失败调用
                .failureHandler(loginFailHandler)
                // JWT认证
                .and().exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler);

        // 退出登录处理
        security.logout()
                .logoutUrl("/api/logout")
                .clearAuthentication(false)
                .logoutSuccessHandler(logoutSuccessHandler);

        // 权限控制
        security.authorizeRequests()
            .antMatchers("/api/login").anonymous()
            // 允许静态资源
            .mvcMatchers(HttpMethod.GET, staticPath).permitAll()
            // 阿里巴巴 druid/
            .antMatchers("/druid/**").anonymous()
            // 允许访问首页
            .antMatchers("/", "/index").permitAll()
            // 放行OPTIONS请求
            .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
            // 自定义匿名访问所有url放行 ： 允许匿名和带权限以及登录用户访问
            .antMatchers(anonymousUrls.toArray(new String[0])).permitAll()
            // 所有请求都需要认证
            .anyRequest().authenticated()
            .and()
            .headers().frameOptions().disable();
            // .and().apply(securityConfigurerAdapter())
    }

}