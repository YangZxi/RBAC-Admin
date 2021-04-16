package cn.xiaosm.yadmin.basic.config.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @author Young
 * @create 2021/4/16
 * @since 1.0.0
 */
@Configuration
public class JacksonConfig {

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


