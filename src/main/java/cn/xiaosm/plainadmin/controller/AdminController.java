/**
 * Copyright: 2019-2020，小树苗(www.xiaosm.cn)
 * FileName: AdminController
 * Author:   Young
 * Date:     2020/6/13 20:27
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * Young         修改时间           版本号             描述
 */
package cn.xiaosm.plainadmin.controller;

import cn.xiaosm.plainadmin.entity.ResponseEntity;
import cn.xiaosm.plainadmin.entity.User;
import cn.xiaosm.plainadmin.service.UserService;
import cn.xiaosm.plainadmin.utils.Response;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 〈一句话功能简述〉
 * 〈〉
 *
 * @author Young
 * @create 2020/6/13
 * @since 1.0.0
 */
@RestController
public class AdminController {

    @Autowired
    UserService userService;

    public ResponseEntity login(@RequestBody User user) {
        // userService.
        String token = "";
        token = JWT.create()
                .withAudience(user.getUsername())
                .withClaim("role", user.getRoleId())
                .withExpiresAt(new Date(System.currentTimeMillis() + 2 * 60 * 1000))
                .sign(Algorithm.HMAC256("1282381264"));
        JWTCreator.Builder builder = JWT.create().withAudience(user.getUsername());

        return Response.buildSuccess(null);

    }

}