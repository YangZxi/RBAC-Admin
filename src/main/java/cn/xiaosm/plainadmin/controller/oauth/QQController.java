/**
 * Copyright: 2019-2020
 * FileName: QQController
 * Author:   Young
 * Date:     2020/12/3 15:31
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * Young         修改时间           版本号             描述
 */
package cn.xiaosm.plainadmin.controller.oauth;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.json.JSONUtil;
import cn.xiaosm.plainadmin.config.security.service.TokenService;
import cn.xiaosm.plainadmin.config.security.service.UserDetailsServiceImpl;
import cn.xiaosm.plainadmin.entity.LoginUser;
import cn.xiaosm.plainadmin.entity.ResponseBody;
import cn.xiaosm.plainadmin.entity.enums.ResponseStatus;
import cn.xiaosm.plainadmin.entity.oauth.QQAuth;
import cn.xiaosm.plainadmin.service.UserService;
import cn.xiaosm.plainadmin.utils.ResponseUtils;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.request.AuthQqRequest;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 〈一句话功能简述〉
 * 〈〉
 *
 * @author Young
 * @create 2020/12/3
 * @since 1.0.0
 */
@RestController
@RequestMapping("/oauth")
public class QQController {

    @Autowired
    UserService userService;
    @Autowired
    UserDetailsServiceImpl userDetailsService;
    @Autowired
    TokenService tokenService;
    @Autowired
    private QQAuth qqAuth;

    @RequestMapping("/render/qq")
    public void renderAuth(HttpServletResponse response) throws IOException {
        AuthRequest authRequest = getAuthRequest();
        response.sendRedirect(authRequest.authorize(AuthStateUtils.createState()));
    }

    @RequestMapping("/callback/qq")
    public Object login(AuthCallback callback, HttpServletRequest request) {
        AuthRequest authRequest = getAuthRequest();
        AuthResponse login = authRequest.login(callback);
        Object body = null;
        if (login.ok()) {
            LoginUser loginUser = (LoginUser) userDetailsService
                    .loadUserByOpenId(((AuthUser) login.getData()).getToken().getOpenId(), "qq");
            // 如果能够查到用户登录信息
            if (Objects.nonNull(loginUser)) {
                // 设置登录用户信息（用户的权限和菜单列表）
                userDetailsService.loadUserInfo(loginUser);
                // 记录登录足迹
                userService.addLoginTrack(loginUser.getId(), ServletUtil.getClientIP(request));
                // 返回 token
                body = new HashMap<String, Object>(){{
                    put("code", ResponseStatus.SUCCESS.getCode());
                    put("msg", "登录成功");
                    // 根据认证创建 Token
                    put("token", tokenService.createToken(loginUser));
                }};

            } else {
                // 该账号未绑定此快捷登录方式
                body = new ResponseBody(ResponseStatus.OAUTH_UNBIND, "当前QQ暂未绑定平台账户，请注册");
            }

        } else {

        }
        String script = "<script>window.opener.postMessage('{}', '{}');window.close();</script>";
        script = StrUtil.format(script, JSONUtil.toJsonStr(body), "http://localhost:8080");
        return script;
    }

    private AuthRequest getAuthRequest() {
        return new AuthQqRequest(AuthConfig.builder()
                .clientId(qqAuth.getAppid())
                .clientSecret(qqAuth.getAppkey())
                .redirectUri(qqAuth.getCallback())
                .build());
    }

}