/**
 * Copyright: 2019-2020，小树苗(www.xiaosm.cn)
 * FileName: LogoutSuccessHandler
 * Author:   Young
 * Date:     2020/6/19 8:04
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * Young         修改时间           版本号             描述
 */
package cn.xiaosm.plainadmin.config.security.handler;

import cn.hutool.http.HttpStatus;
import cn.xiaosm.plainadmin.config.security.service.TokenService;
import cn.xiaosm.plainadmin.entity.LoginUser;
import cn.xiaosm.plainadmin.exception.LoginException;
import cn.xiaosm.plainadmin.utils.CacheUtils;
import cn.xiaosm.plainadmin.utils.ResponseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 〈一句话功能简述〉
 * 〈〉
 *
 * @author Young
 * @create 2020/6/19
 * @since 1.0.0
 */
@Component
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    TokenService tokenService;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String uuid = tokenService.getUUID(tokenService.getToken(request));
        response.setHeader("Access-Control-Allow-Origin", "*");
        try {
            logger.info("用户{}，退出登录成功", ((LoginUser) CacheUtils.getObject(uuid)).getUsername());
        } catch (NullPointerException e) {
            throw new LoginException("当前登录信息已过期");
        }
        CacheUtils.removeObject(uuid);
        ResponseUtils.sendError(response, "退出登录成功", HttpStatus.HTTP_OK);
    }
}