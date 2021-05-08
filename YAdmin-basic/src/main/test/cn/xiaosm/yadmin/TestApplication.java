package cn.xiaosm.yadmin;

import cn.hutool.script.ScriptUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.constraints.NotNull;

/**
 * @author Young
 * @create 2021/3/25
 * @since 1.0.0
 */
@SpringBootTest
@SpringBootConfiguration
public class TestApplication {

    @Test
    public void test1() {
        computer(null, 5);
    }
    
    public void computer(@NotNull Integer a, Integer b) {
        if (a == null) a = 1;
        System.out.println(a * b);
    }

}