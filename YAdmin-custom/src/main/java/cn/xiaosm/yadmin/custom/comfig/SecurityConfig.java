package cn.xiaosm.yadmin.custom.comfig;

import cn.xiaosm.yadmin.basic.config.security.SecurityAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 * 项目安全框架，如果不需要使用可以删除以下注解或本类
 *
 * @author Young
 * @create 2021/3/24
 * @since 1.0.0
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // 开启spring方法级安全
public class SecurityConfig extends SecurityAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
        // 放行以下路径
        web.ignoring().mvcMatchers("/test/**");
    }

    @Override
    protected void configure(HttpSecurity security) throws Exception {
        super.configure(security);
    }

}