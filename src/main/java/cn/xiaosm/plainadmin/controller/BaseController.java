/**
 * Copyright: 2019-2020，小树苗(www.xiaosm.cn)
 * FileName: BaseController
 * Author:   Young
 * Date:     2020/6/13 15:25
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * Young         修改时间           版本号             描述
 */
package cn.xiaosm.plainadmin.controller;

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
public class BaseController {

    private ResponseEntity responseBody(ResponseStatus status, Object data) {
        ResponseEntity apiResponseEntity = new ResponseEntity();
        apiResponseEntity.setCode(status.getCode());
        apiResponseEntity.setMsg(status.getMsg());
        apiResponseEntity.setData(data);
        return apiResponseEntity;
    }

    private ResponseEntity responseBody(ResponseStatus status, String msg, Object data) {
        ResponseEntity apiResponseEntity = new ResponseEntity();
        apiResponseEntity.setCode(status.getCode());
        apiResponseEntity.setMsg(msg);
        apiResponseEntity.setData(data);
        return apiResponseEntity;
    }

    public ResponseEntity responseSuccess(Object data) {
        return this.responseBody(ResponseStatus.SUCCESS, data);
    }

    public ResponseEntity responseSuccess(String msg, Object data) {
        return this.responseBody(ResponseStatus.SUCCESS, msg, data);
    }

    public ResponseEntity responseError(Object data) {
        return this.responseBody(ResponseStatus.ERROR_PARAM, data);
    }

    public ResponseEntity responseError(String msg, Object data) {
        return this.responseBody(ResponseStatus.ERROR_PARAM, msg, data);
    }

    public ResponseEntity responseFail(Object data) {
        return this.responseBody(ResponseStatus.FAIL, data);
    }

    public ResponseEntity responseFail(String msg, Object data) {
        return this.responseBody(ResponseStatus.FAIL, msg, data);
    }

}