package cn.xiaosm.yadmin.basic.config.jackson;

import cn.xiaosm.yadmin.basic.entity.enums.BaseEnum;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
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
     * 新增对枚举的反序列化操作
     * error to avoid exception, disable SerializationFeature.FAIL_ON_EMPTY_BEANS
     * @return
     */
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper()
            .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        SimpleModule module = new SimpleModule();
        module.addDeserializer(BaseEnum.class, new BaseEnumJsonSerializer());
        mapper.registerModule(module);
        return mapper;
    }

}


