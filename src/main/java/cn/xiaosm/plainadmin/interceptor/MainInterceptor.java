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
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    private Long timer = 0l;

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
        // 判断请求的
        timer = System.currentTimeMillis();
        response.setCharacterEncoding("UTF-8");

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
        if ("/eroor".equals(request.getRequestURI())) {
            // logger.info("客户端IP=[{}]，请求地址=[{}]，请求类型=[{}]",
            //         ServletUtil.getClientIP(request),
            //         request.getRequestURI(), request.getMethod());
        } else {
            logger.info("客户端IP=[{}]，请求地址=[{}]，请求类型=[{}]，本次请求耗时={}ms",
                    ServletUtil.getClientIP(request),
                    request.getRequestURI(), request.getMethod(),
                    System.currentTimeMillis() - timer);
        }
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