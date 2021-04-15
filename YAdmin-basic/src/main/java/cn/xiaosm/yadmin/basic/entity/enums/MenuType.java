package cn.xiaosm.yadmin.basic.entity.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 菜单类型
 *
 * @author Young
 * @create 2021/3/29
 * @since 1.0.0
 */
public enum MenuType implements BaseEnum<Integer> {
    LEVEL_1(1, "一级菜单")
    ,LEVEL_2(2, "二级菜单")
    ,BUTTON(3, "按钮操作")
    ;

    @EnumValue
    @JsonValue
    private final Integer value;
    private final String label;

    MenuType(Integer value, String label) {
        this.value = value;
        this.label = label;
    }

    @Override
    public Integer getValue() {
        return value;
    }
    public String getLabel() {
        return label;
    }
}