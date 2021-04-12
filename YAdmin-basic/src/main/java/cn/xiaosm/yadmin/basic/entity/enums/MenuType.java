package cn.xiaosm.yadmin.basic.entity.enums;

/**
 * 菜单类型
 *
 * @author Young
 * @create 2021/3/29
 * @since 1.0.0
 */
public enum MenuType {
    LEVEL_1(1, "一级菜单")
    ,LEVEL_2(2, "二级菜单")
    ,BUTTON(3, "权限操作")
    ;

    private Integer value;
    private String label;

    MenuType(Integer value, String label) {
        this.value = value;
        this.label = label;
    }

    public Integer value() {
        return value;
    }
    public String label() {
        return label;
    }
}