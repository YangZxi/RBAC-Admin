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

import cn.xiaosm.plainadmin.entity.ResponseEntity;
import cn.xiaosm.plainadmin.entity.ResponseStatus;

/**
 * 〈一句话功能简述〉
 * 〈〉
 *
 * @author Young
 * @create 2020/6/13
 * @since 1.0.0
 */
public class Response {

    private static ResponseEntity responseBody(ResponseStatus status, Object data) {
        ResponseEntity body = new ResponseEntity();
        body.setCode(status.getCode());
        if (data instanceof String) {
            body.setMsg(data.toString());
        } else {
            body.setMsg(status.getMsg());
        }
        body.setData(data);
        return body;
    }

    private static ResponseEntity responseBody(ResponseStatus status, String msg, Object data) {
        ResponseEntity apiResponseEntity = new ResponseEntity();
        apiResponseEntity.setCode(status.getCode());
        apiResponseEntity.setMsg(msg);
        apiResponseEntity.setData(data);
        return apiResponseEntity;
    }

    public static ResponseEntity buildSuccess(Object data) {
        return responseBody(ResponseStatus.SUCCESS, data);
    }

    public static ResponseEntity buildSuccess(String msg, Object data) {
        return responseBody(ResponseStatus.SUCCESS, msg, data);
    }

    public static ResponseEntity buildError(Object data) {
        return responseBody(ResponseStatus.ERROR_PARAM, data);
    }

    public static ResponseEntity buildError(String msg, Object data) {
        return responseBody(ResponseStatus.ERROR_PARAM, msg, data);
    }

    public static ResponseEntity buildFail(Object data) {
        return responseBody(ResponseStatus.FAIL, data);
    }

    public static ResponseEntity buildFail(String msg, Object data) {
        return responseBody(ResponseStatus.FAIL, msg, data);
    }

}