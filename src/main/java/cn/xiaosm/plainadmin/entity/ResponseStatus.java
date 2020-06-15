/**
 * Copyright: 2019-2020，小树苗(www.xiaosm.cn)
 * FileName: ResponseCode
 * Author:   Young
 * Date:     2020/6/13 15:23
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * Young         修改时间           版本号             描述
 */
package cn.xiaosm.plainadmin.entity;

/**
 * 〈一句话功能简述〉
 * 〈〉
 *
 * @author Young
 * @create 2020/6/13
 * @since 1.0.0
 */
public enum ResponseStatus {

    FAIL(0, "失败"),
    SUCCESS(1, "成功"),
    ERROR_PARAM(400, "参数错误"),
    DATA_NULL(404, "查询数据空");

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    ResponseStatus(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}