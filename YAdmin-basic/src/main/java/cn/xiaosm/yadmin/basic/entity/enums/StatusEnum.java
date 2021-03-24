/**
 * Copyright: 2019-2020
 * FileName: StatusEnum
 * Author:   Young
 * Date:     2020/6/21 15:48
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * Young         修改时间           版本号             描述
 */
package cn.xiaosm.yadmin.basic.entity.enums;

/**
 * 〈一句话功能简述〉
 * 〈〉
 *
 * @author Young
 * @create 2020/6/21
 * @since 1.0.0
 */
public enum StatusEnum {

    DISABLED(0, "禁用"),
    ENABLED(1, "启用"),
    DELETED(2, "删除");

    private int code;
    private String label;

    StatusEnum(int code, String label) {
        this.code = code;
        this.label = label;
    }

    public int getCode() {
        return code;
    }

    public StatusEnum setCode(int code) {
        this.code = code;
        return this;
    }

    public String getLabel() {
        return label;
    }

    public StatusEnum setLabel(String label) {
        this.label = label;
        return this;
    }
}