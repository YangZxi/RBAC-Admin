package cn.xiaosm.yadmin.basic.controller.oauth;

import me.zhyd.oauth.model.AuthCallback;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Young
 * @create 2021/4/15
 * @since 1.0.0
 */
public interface AuthLoginHandler {

    /**
     * 跳转到快捷登录的平台网址
     * @param response
     * @throws IOException
     */
    void renderAuth(HttpServletResponse response) throws IOException;

    /**
     * 平台的回调地址
     * @param callback 包装了平台用于登录的key
     * @param request
     * @return
     */
    String login(AuthCallback callback, HttpServletRequest request);

}