/**
 * Copyright: 2019-2020
 * FileName: PropEnum
 * Author:   Young
 * Date:     2020/6/28 12:50
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
 * @create 2020/6/28
 * @since 1.0.0
 */
public enum PropType {

    EMAIL(0, "email");

    private int order;
    private String value;

    PropType(int order, String value) {
        this.order = order;
        this.value = value;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}