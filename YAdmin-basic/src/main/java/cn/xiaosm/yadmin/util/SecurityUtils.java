package cn.xiaosm.yadmin.util;

import cn.xiaosm.yadmin.entity.LoginUser;
import cn.xiaosm.yadmin.exception.LoginException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 〈一句话功能简述〉
 * 〈〉
 *
 * @author Young
 * @create 2020/6/29
 * @since 1.0.0
 */
public class SecurityUtils {

    public static Authentication getAuthentication() {
        try {
            return SecurityContextHolder.getContext().getAuthentication();
        } catch (Exception e) {
            throw new LoginException("登录信息过期");
        }
    }

    public static LoginUser getUserDetails() {
        try {
            return (LoginUser) getAuthentication().getPrincipal();
        } catch (Exception e) {
            throw new LoginException("登录信息过期");
        }
    }


}