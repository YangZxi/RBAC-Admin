/**
 * Copyright: 2019-2020，小树苗(www.xiaosm.cn)
 * FileName: JwtInterceptor
 * Author:   Young
 * Date:     2020/3/8 14:08
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
 * @create 2020/3/8
 * @since 1.0.0
 */
public class JwtInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /*String token = request.getHeader("token");
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        if (method.getName().equals("login")) {
            return true;
        }
        PrintWriter writer;
        response.setCharacterEncoding("UTF-8");
        if (token == null) {
            writer = response.getWriter();
            writer.println("无Token，请先获取Token");
            return false;
        }
        // 获取token中的name
        String name = JWT.decode(token).getAudience().get(0);
        // 验证token
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256("1282381264"))
                .withClaim("isAdmin", 1)
                .build();
        try {
            DecodedJWT verify = verifier.verify(token);
//            System.out.println(verify.getHeader());
//            System.out.println(verify.getPayload());
//            System.out.println(verify.getSignature());
//            System.out.println(verify.getType());
//            System.out.println(verify.getAlgorithm());
//            System.out.println(verify.getId());
        } catch (JWTVerificationException e) {
            writer = response.getWriter();
            writer.println("验证Token失败，请重新获取");
            return false;
        }*/
        return true;
    }
}