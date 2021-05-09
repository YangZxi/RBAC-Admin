package cn.xiaosm.yadmin.basic.config.security.service;

import cn.hutool.extra.servlet.ServletUtil;
import cn.xiaosm.yadmin.basic.entity.LoginUser;
import cn.xiaosm.yadmin.basic.entity.User;
import cn.xiaosm.yadmin.basic.exception.CanShowException;
import cn.xiaosm.yadmin.basic.service.UserService;
import cn.xiaosm.yadmin.basic.util.cache.CacheUtils;
import cn.xiaosm.yadmin.basic.util.ServletUtils;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Objects;
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

    @Autowired
    UserService userService;

    // 存放 Token 的头部名称
    @Value("${security.token.header}")
    private String AUTH_HEADER;
    // Token 前缀
    @Value("${security.token.prefix}")
    private String TOKEN_PREFIX;
    // 用于加密的密钥
    private String SECRET_KEY;
    // Token 的 claim 信息
    private final String JWT_CLAIM_UUID = "UUID";
    // 单位 分钟
    private final int MINUTE = 60 * 1000;
    // 设置 Token 到期时间 秒
    private int EXPIRES;
    // Token 验证器
    private JWTVerifier verifier = null;


    @Value("${security.token.expires}")
    public void setEXPIRES(int EXPIRES) {
        this.EXPIRES = EXPIRES * MINUTE;
    }

    @Value("${security.token.secret-key}")
    public void setSECRET_KEY(String SECRET_KEY) {
        this.SECRET_KEY = SECRET_KEY;
        this.verifier = JWT.require(Algorithm.HMAC256(SECRET_KEY)).build();
    }



    /**
     * 创建 Token
     * @param loginUser
     * @return
     */
    public String createToken(LoginUser loginUser) {
        // 先通过旧的UUID删除内存中的登录信息
        CacheUtils.removeObject(loginUser.getUuid());
        String uuid = UUID.randomUUID().toString();
        loginUser.setUuid(uuid);
        String token = JWT.create()
                .withAudience(loginUser.getUsername())
                .withClaim(JWT_CLAIM_UUID, uuid)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRES))
                .sign(Algorithm.HMAC256(SECRET_KEY));
        // JWTCreator.Builder builder = JWT.create().withAudience(user.getUsername());
        // 更新数据库中的 UUID
        userService.updateById(new User(loginUser.getId(), uuid));
        // 保存登录信息到内存
        CacheUtils.saveObject(uuid, loginUser, EXPIRES);
        return token;
    }

    /**
     * 获取请求头中的 token
     * @param request
     * @return
     */
    public String getToken(HttpServletRequest request) {
        String token = "";
        try {
            token = request.getHeader(AUTH_HEADER);
            if (Objects.isNull(token)) token = ServletUtil.getParamMap(request).get(AUTH_HEADER);
            if (Objects.isNull(token)) token = ServletUtil.getParamMap(request).get("_token");
        } catch (NullPointerException e) {
            return "";
        }
        return Objects.isNull(token) ? "" : token.replace(TOKEN_PREFIX, "").trim();
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
            CacheUtils.removeObject(this.getUUID(token));
            // e.printStackTrace();
            log.error("Token已过期，请重新登录 >>> {}", e.getMessage());
            throw new CanShowException("Token已过期，请重新登录");
        }
    }

    public String getUUID(String token) {
        try {
            return JWT.decode(token).getClaim(JWT_CLAIM_UUID).asString();
        } catch (JWTDecodeException e) {
            return "";
        }
    }

    /**
     * 从内存中获取 UserDetails 信息
     * @return
     */
    public LoginUser getLoginUser() {
        return this.getLoginUser(ServletUtils.getRequest());
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
        return (LoginUser) CacheUtils.getObject(this.getUUID(token));
    }
}