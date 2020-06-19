/**
 * Copyright: 2019-2020 WWW.XIAOSM.CN
 * FileName:    CorsInterceptor
 * Author:      Young
 * Date:        2020/5/6 21:49
 * Description:
 * History:
 */
package cn.xiaosm.plainadmin.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 〈一句话功能简述〉
 * 〈〉
 *
 * @author Young
 * @create 2020/5/6
 * @version 1.0.0
 */
public class CorsInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Headers", "*");
//        response.setHeader("Access-Control-Allow-Credentials", "true");
        return true;
    }

}