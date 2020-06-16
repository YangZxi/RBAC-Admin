/**
 * Copyright: 2019-2020，小树苗(www.xiaosm.cn)
 * FileName: TokenProvider
 * Author:   Young
 * Date:     2020/6/15 19:25
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * Young         修改时间           版本号             描述
 */
package cn.xiaosm.plainadmin.config.security.service;

import cn.xiaosm.plainadmin.entity.LoginUser;
import cn.xiaosm.plainadmin.entity.User;
import cn.xiaosm.plainadmin.utils.MemoryUtils;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * 〈一句话功能简述〉
 * 〈〉
 *
 * @author Young
 * @create 2020/6/15
 * @since 1.0.0
 */
@Component
public class TokenService {

    private final String AUTH_HEADER = "Authorization";
    private final String JWT_CLAIM_KEY = "UUID";
    private final int SECOND = 1000;
    private final int MINUTE = 60 * SECOND;

    /**
     * 创建 Token
     * @param loginUser
     * @return
     */
    public String createToken(LoginUser loginUser) {
        // String authorities = authentication.getAuthorities().stream()
        //         .map(GrantedAuthority::getAuthority)
        //         .collect(Collectors.joining(","));
        String uuid = UUID.randomUUID().toString();
        loginUser.setUuid(uuid);
        String token = JWT.create()
                .withAudience(loginUser.getUsername())
                .withClaim(JWT_CLAIM_KEY, uuid)
                .withExpiresAt(new Date(System.currentTimeMillis() + 10 * MINUTE))
                .sign(Algorithm.HMAC256("1282381264"));
        // JWTCreator.Builder builder = JWT.create().withAudience(user.getUsername());
        MemoryUtils.saveObject(uuid, loginUser);
        return token;
    }

    /**
     * 获取请求头中的 token
     * @param request
     * @return
     */
    public String getToken(HttpServletRequest request) {
        return request.getHeader(AUTH_HEADER);
    }

    public LoginUser getLoginUser(HttpServletRequest request) {
        String token = this.getToken(request);
        // 获取token中的uuid
        String uuid = JWT.decode(token).getClaim(JWT_CLAIM_KEY).asString();
        // 验证token
        // JWTVerifier verifier = JWT.require(Algorithm.HMAC256("1282381264"))
        //         .build();

        return (LoginUser) MemoryUtils.getObject(uuid);
    }
}