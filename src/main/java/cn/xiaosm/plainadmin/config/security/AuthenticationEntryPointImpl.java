/**
 * Copyright: 2019-2020，小树苗(www.xiaosm.cn)
 * FileName: JwtAuthenticationEntryPoint
 * Author:   Young
 * Date:     2020/6/15 9:57
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * Young         修改时间           版本号             描述
 */
package cn.xiaosm.plainadmin.config.security;

import cn.xiaosm.plainadmin.entity.ResponseStatus;
import cn.xiaosm.plainadmin.utils.ResponseUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 〈一句话功能简述〉
 * 没有通过认证处理类
 *
 * @author Young
 * @create 2020/6/15
 * @since 1.0.0
 */
@Log4j2
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) {
        log.debug("请先通过认证在进行操作");
        // 当用户尝试访问安全的REST资源而不提供任何凭据时，将调用此方法发送401 响应
        ResponseUtils.sendError(response, ResponseStatus.AUTHENTICATION_EXPIRE,
                authException == null ? "Unauthorized" : "请先通过认证在进行操作",
                HttpServletResponse.SC_UNAUTHORIZED);
    }
}