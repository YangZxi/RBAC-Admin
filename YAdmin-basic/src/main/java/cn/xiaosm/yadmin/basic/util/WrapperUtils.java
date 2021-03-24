package cn.xiaosm.yadmin.basic.util;

import cn.xiaosm.yadmin.basic.entity.vo.Pager;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.Objects;

/**
 * mybatis-plus查询对象快速包装类
 *
 * @author Young
 * @create 2020/12/11
 * @since 1.0.0
 */
public class WrapperUtils {

    /**
     * 生成用于搜索功能通用的 wrapper 对象
     * @param wrapper
     * @param pager
     * @return
     */
    public static QueryWrapper bindSearch(QueryWrapper wrapper, Pager pager) {
        if (Objects.nonNull(pager.getStartTime())) {
            wrapper.ge("create_time", pager.getStartTime());
        }
        if (Objects.nonNull(pager.getEndTime())) {
            wrapper.le("create_time", pager.getEndTime());
        }
        if (Objects.nonNull(pager.getStatus())) {
            wrapper.eq("status", pager.getStatus());
        } else {
            // 如果没传将只查询 启用 和 禁用
            // wrapper.lt("status", StatusEnum.DELETED.getCode());
        }
        return wrapper;
    }

}