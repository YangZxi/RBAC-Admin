package cn.xiaosm.yadmin.util;

import cn.hutool.json.JSONUtil;
import cn.xiaosm.yadmin.entity.ResponseBody;
import cn.xiaosm.yadmin.entity.enums.ResponseStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * 〈一句话功能简述〉
 * 〈〉
 *
 * @author Young
 * @create 2020/6/13
 * @since 1.0.0
 */
public class ResponseUtils {

    public static void sendToken(HttpServletResponse response, String token) {
        writeBody(response, new HashMap<String, Object>(){{
            put("code", 200);
            put("msg", "登录成功");
            put("token", token);
        }}, HttpServletResponse.SC_OK);
    }

    public static ResponseBody build(ResponseStatus status, String msg) {
        return new ResponseBody(status, msg);
    }

    public static ResponseBody build(ResponseStatus status, Object data) {
        return new ResponseBody(status, data);
    }

    public static ResponseBody build(ResponseStatus status, String msg, Object data) {
        return new ResponseBody(status, msg, data);
    }

    public static ResponseBody buildSuccess(String msg) {
        return new ResponseBody(ResponseStatus.SUCCESS, msg);
    }

    public static ResponseBody buildSuccess(Object data) {
        return new ResponseBody(ResponseStatus.SUCCESS, data);
    }

    public static ResponseBody buildSuccess(String msg, Object data) {
        return new ResponseBody(ResponseStatus.SUCCESS, msg, data);
    }

    public static ResponseBody buildFail(String msg) {
        return new ResponseBody(ResponseStatus.FAIL, msg);
    }

    public static ResponseBody buildFail(Object data) {
        return new ResponseBody(ResponseStatus.FAIL, data);
    }

    public static ResponseBody buildFail(String msg, Object data) {
        return new ResponseBody(ResponseStatus.FAIL, msg, data);
    }

    public static ResponseBody buildError(String msg) {
        return new ResponseBody(ResponseStatus.ERROR, msg);
    }

    public static ResponseBody buildError(Object data) {
        return new ResponseBody(ResponseStatus.ERROR, data);
    }

    public static ResponseBody buildError(String msg, Object data) {
        return new ResponseBody(ResponseStatus.ERROR, msg, data);
    }

    // 发送请求

    public static void buildSuccess(HttpServletResponse response, String msg) {
        writeBody(response, buildSuccess(msg), HttpServletResponse.SC_OK);
    }

    public static void sendError(HttpServletResponse response, String msg, int sc) {
        writeBody(response, build(ResponseStatus.SUCCESS, msg), sc);
    }

    public static void sendError(HttpServletResponse response, ResponseStatus status, String msg, int sc) {
        writeBody(response, new ResponseBody(status, msg), sc);
    }

    private static<T> void writeBody(HttpServletResponse response, T entity, int sc) {
        // response.setHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(sc);
        try {
            response.getWriter().write(JSONUtil.toJsonStr(entity));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}