/**
 * Copyright: 2019-2020，小树苗(www.xiaosm.cn)
 * FileName: JwtAccessHandler
 * Author:   Young
 * Date:     2020/6/15 9:49
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * Young         修改时间           版本号             描述
 */
package cn.xiaosm.plainadmin.config.security.handler;

import cn.xiaosm.plainadmin.entity.enums.ResponseStatus;
import cn.xiaosm.plainadmin.utils.ResponseUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 〈一句话功能简述〉
 * 〈当没有认证时的处理类〉
 *
 * @author Young
 * @create 2020/6/15
 * @since 1.0.0
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException e) {
        // 当用户没有授权（拥有JWT）的情况下访问受保护的路径和资源时
        // 将调用此方法 返回 403 Forbidden 响应
        ResponseUtils.sendError(response, ResponseStatus.AUTHORITIES_DENIED,
                e.getMessage(), HttpServletResponse.SC_FORBIDDEN);
    }
}