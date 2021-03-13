/**
 * Copyright: 2019-2020，小树苗(www.xiaosm.cn)
 * FileName: MainInterceptor
 * Author:   Young
 * Date:     2020/3/8 11:24
 * Description: 核心拦截器
 * History:
 */
package cn.xiaosm.yadmin.interceptor;

import cn.xiaosm.yadmin.config.security.service.TokenService;
import cn.xiaosm.yadmin.service.LogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 〈一句话功能简述〉
 * 〈日志拦截器〉
 *
 * @author Young
 * @create 2020/6/25
 * @since 1.0.0
 */
public class LogInterceptor implements HandlerInterceptor {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    TokenService tokenService;
    @Autowired
    LogService logService;

    /**
     * 在DispatcherServlet之前执行
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // try {
        //     HandlerMethod method = (HandlerMethod) handler;
        //     // 获取方法上的注解
        //     LogRecord logRecord = method.getMethodAnnotation(LogRecord.class);
        //     if (Objects.nonNull(logRecord)) {
        //         Map<String, String> map = new HashMap<>();
        //         map.put("params", StrUtil.sub(JSONUtil.toJsonStr(request.getParameterMap()), 1, -2));
        //         map.put("data", request.getReader().readLine());
        //         String methodName = new StringBuffer()
        //                 .append(method.getBeanType().getName()).append(".")
        //                 .append(method.getMethod().getName()).toString();
        //         logService.addEntity(
        //                 new Log().setContent(JSONUtil.toJsonStr(map))
        //                 .setType("INFO")
        //                 .setMethod(methodName)
        //                 .setUserId(tokenService.getLoginUser(request).getId())
        //                 .setIp(ServletUtil.getClientIP(request))
        //                 .setTitle(logRecord.value())
        //         );
        //     }
        // } catch (ClassCastException e) {
        //     logger.debug(e.getMessage());
        // }

        return true;
    }

    /**
     * 在Controller执行之后的DispatcherServlet之后执行
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // System.out.println(1);
    }

    /**
     * 在页面渲染完成返回给客户端之前执行
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // System.out.println(2);
    }
}