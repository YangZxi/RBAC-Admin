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
package cn.xiaosm.yadmin.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

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
     * 解决 jackson 序列化报错问题
     * error to avoid exception, disable SerializationFeature.FAIL_ON_EMPTY_BEANS
     * @return
     */
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper().disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    }

}