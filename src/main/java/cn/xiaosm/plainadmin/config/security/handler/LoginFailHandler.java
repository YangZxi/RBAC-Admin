/**
 * Copyright: 2019-2020，小树苗(www.xiaosm.cn)
 * FileName: LoginSuccessHandler
 * Author:   Young
 * Date:     2020/6/15 19:07
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * Young         修改时间           版本号             描述
 */
package cn.xiaosm.plainadmin.config.security.handler;

import cn.xiaosm.plainadmin.entity.ResponseStatus;
import cn.xiaosm.plainadmin.utils.ResponseUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 〈一句话功能简述〉
 * 〈登录认证失败处理类〉
 *
 * @author Young
 * @create 2020/6/15
 * @since 1.0.0
 */
@Component
public class LoginFailHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException e) {
        ResponseUtils.sendError(response, ResponseStatus.AUTHENTICATION_EXPIRE,
                e.getMessage(), HttpServletResponse.SC_UNAUTHORIZED);
    }

}