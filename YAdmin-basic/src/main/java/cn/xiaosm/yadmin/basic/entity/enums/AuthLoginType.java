package cn.xiaosm.yadmin.basic.entity.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;

/**
 * @author Young
 * @create 2021/4/15
 * @since 1.0.0
 */
public enum AuthLoginType implements IEnum<String> {
    QQ("QQ", "QQ快捷登录")
    ;

    // @EnumValue
    private final String type;
    private final String desc;

    AuthLoginType(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    @Override
    public String getValue() {
        return type;
    }

    public String getDesc() {
        return desc;
    }
}