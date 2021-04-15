package cn.xiaosm.yadmin.basic.entity.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;

import java.io.Serializable;

/**
 * @author Young
 * @create 2021/4/14
 * @since 1.0.0
 */
public interface BaseEnum<T> {

    T getValue();

}