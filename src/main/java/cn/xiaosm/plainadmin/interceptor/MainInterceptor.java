/**
 * Copyright: 2019-2020，小树苗(www.xiaosm.cn)
 * FileName: MainInterceptor
 * Author:   Young
 * Date:     2020/3/8 11:24
 * Description: 核心拦截器
 * History:
 */
package cn.xiaosm.plainadmin.interceptor;

import cn.hutool.extra.servlet.ServletUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 〈一句话功能简述〉
 * 〈核心拦截器〉
 *
 * @author Young
 * @create 2020/3/8
 * @since 1.0.0
 */
public class MainInterceptor implements HandlerInterceptor {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

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
        response.setCharacterEncoding("UTF-8");
        System.out.println("\n=============================================================");
        System.out.println("主拦截器执行了");
        // System.out.println("客户端IP：" + ServletUtil.getClientIP(request));
        System.out.println("请求地址：" + request.getRequestURI());
        try {
            HandlerMethod method = (HandlerMethod) handler;
            System.out.println("请求方法：" + method.getMethod().getName());
        }catch (ClassCastException e) {
            System.err.println(e.getMessage() + "无法转换为方法处理器");
        }
        System.out.println("请求时间：" + new Date(System.currentTimeMillis()));
        System.out.println("=============================================================\n");

        logger.info("客户端IP:{}，请求地址=[{}]",
                ServletUtil.getClientIP(request),
                request.getRequestURI());

//        response.sendRedirect("http://www.baidu.com");
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
//        System.out.println(1);
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
//        System.out.println(2);
    }
}