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
import cn.xiaosm.plainadmin.service.UserService;
import cn.xiaosm.plainadmin.utils.MemoryUtils;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;

/**
 * 〈一句话功能简述〉
 * 〈〉
 *
 * @author Young
 * @create 2020/6/15
 * @since 1.0.0
 */
@Log4j2
@Component
public class TokenService {

    // 存放 Token 的头部名称
    private final String AUTH_HEADER = "Authorization";
    // Token 前缀
    private final String TOKEN_PREFIX = "Bearer";
    // Token 的 claim 信息
    private final String JWT_CLAIM_UUID = "UUID";
    // 用于加密的字段
    private final String HMAC256= "1282381264";
    private final int SECOND = 1000;
    private final int MINUTE = 60 * SECOND;
    // 设置 Token 到期时间 十分钟
    private final int EXPIRES = 5 * MINUTE;
    private JWTVerifier verifier = JWT.require(Algorithm.HMAC256(HMAC256))
            .build();
    @Autowired
    UserService userService;

    /**
     * 创建 Token
     * @param loginUser
     * @return
     */
    public String createToken(LoginUser loginUser) {
        // String authorities = authentication.getAuthorities().stream()
        //         .map(GrantedAuthority::getAuthority)
        //         .collect(Collectors.joining(","));
        // 先通过旧的UUID删除内存中的登录信息
        MemoryUtils.removeObject(loginUser.getUuid());
        String uuid = UUID.randomUUID().toString();
        loginUser.setUuid(uuid);
        String token = JWT.create()
                .withAudience(loginUser.getUsername())
                .withClaim(JWT_CLAIM_UUID, uuid)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRES))
                .sign(Algorithm.HMAC256(HMAC256));
        // JWTCreator.Builder builder = JWT.create().withAudience(user.getUsername());
        // 更新数据库中的 UUID
        userService.updateById(new User(loginUser.getId(), uuid));
        // 保存登录信息到内存
        MemoryUtils.saveObject(uuid, loginUser);
        return token;
    }

    /**
     * 获取请求头中的 token
     * @param request
     * @return
     */
    public String getToken(HttpServletRequest request) {
        try {
            return request.getHeader(AUTH_HEADER)
                    .replaceFirst(TOKEN_PREFIX, "")
                    .trim();
        } catch (NullPointerException e) {
            return "";
        }
    }

    /**
     * 校验Token是否过期
     * @param token
     * @return
     */
    public boolean verifyToken(String token) {
        try {
            verifier.verify(token);
            return true;
        } catch (TokenExpiredException e) {
            MemoryUtils.removeObject(this.getUUID(token));
            e.printStackTrace();
            log.error("Token已过期，请重新登录 >>> {}", e.getMessage());
        }
        return false;
    }

    public String getUUID(String token) {
        return JWT.decode(token).getClaim(JWT_CLAIM_UUID).asString();
    }

    /**
     * 从内存中获取 UserDetails 信息
     * @param request
     * @return
     */
    public LoginUser getLoginUser(HttpServletRequest request) {
        String token = this.getToken(request);
        // 获取token中的uuid
        if (StringUtils.isBlank(token)) {
            // throw new NullPointerException("Token为空");
            return null;
        }

        return (LoginUser) MemoryUtils.getObject(this.getUUID(token));
    }
}