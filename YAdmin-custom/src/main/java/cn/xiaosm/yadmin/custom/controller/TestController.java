package cn.xiaosm.yadmin.custom.controller;

import cn.xiaosm.yadmin.basic.entity.ResponseBody;
import cn.xiaosm.yadmin.basic.util.ResponseUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Young
 * @create 2021/3/24
 * @since 1.0.0
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/1")
    public ResponseBody test1() {
        return ResponseUtils.buildSuccess("ok");
    }

}