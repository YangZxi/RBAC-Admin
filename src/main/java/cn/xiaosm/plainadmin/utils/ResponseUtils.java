/**
 * Copyright: 2019-2020，小树苗(www.xiaosm.cn)
 * FileName: ResponseBuild
 * Author:   Young
 * Date:     2020/6/13 15:34
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * Young         修改时间           版本号             描述
 */
package cn.xiaosm.plainadmin.utils;

import cn.hutool.json.JSONUtil;
import cn.xiaosm.plainadmin.entity.ResponseEntity;
import cn.xiaosm.plainadmin.entity.ResponseStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

    public static ResponseEntity buildSuccess(String msg) {
        return new ResponseEntity(ResponseStatus.SUCCESS, msg);
    }

    public static ResponseEntity buildSuccess(Object data) {
        return new ResponseEntity(ResponseStatus.SUCCESS, data);
    }

    public static ResponseEntity buildSuccess(String msg, Object data) {
        return new ResponseEntity(ResponseStatus.SUCCESS, msg, data);
    }

    public static ResponseEntity buildFail(String msg) {
        return new ResponseEntity(ResponseStatus.FAIL, msg);
    }

    public static ResponseEntity buildFail(Object data) {
        return new ResponseEntity(ResponseStatus.FAIL, data);
    }

    public static ResponseEntity buildFail(String msg, Object data) {
        return new ResponseEntity(ResponseStatus.FAIL, msg, data);
    }

    public static ResponseEntity buildError(String msg) {
        return new ResponseEntity(ResponseStatus.ERROR, msg);
    }

    public static ResponseEntity buildError(Object data) {
        return new ResponseEntity(ResponseStatus.ERROR, data);
    }

    public static ResponseEntity buildError(String msg, Object data) {
        return new ResponseEntity(ResponseStatus.ERROR, msg, data);
    }

    // 发送请求

    public static void buildSuccess(HttpServletResponse response, String msg) {
        writeBody(response, buildSuccess(msg), HttpServletResponse.SC_OK);
    }

    public static void sendError(HttpServletResponse response, String msg, int sc) {
        writeBody(response, buildError(msg), sc);
    }

    public static void sendError(HttpServletResponse response, ResponseStatus status, String msg, int sc) {
        writeBody(response, new ResponseEntity(status, msg), sc);
    }

    private static<T> void writeBody(HttpServletResponse response, T entity, int sc) {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(sc);
        try {
            response.getWriter().write(JSONUtil.toJsonStr(entity));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}