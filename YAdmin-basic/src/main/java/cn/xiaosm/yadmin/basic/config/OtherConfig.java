/**
 * Copyright: 2019-2020，小树苗(www.xiaosm.cn)
 * FileName: OtherConfig
 * Author:   Young
 * Date:     2020/6/14 19:14
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * Young         修改时间           版本号             描述
 */
package cn.xiaosm.yadmin.basic.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 〈其他配置〉
 *
 * @author Young
 * @create 2020/6/14
 * @since 1.0.0
 */
@Configuration
public class OtherConfig {

    /**
     * 密码加密处理器
     * @return
     */
    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}