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

import cn.xiaosm.plainadmin.utils.ResponseUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 〈一句话功能简述〉
 * 〈认证成功处理类〉
 *
 * @author Young
 * @create 2020/6/15
 * @since 1.0.0
 */
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        ResponseUtils.sendSuccess(response, "登录成功");
    }

}