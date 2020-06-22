/**
 * Copyright: 2019-2020，小树苗(www.xiaosm.cn)
 * FileName: OperatorServiceImpl
 * Author:   Young
 * Date:     2020/6/16 10:33
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * Young         修改时间           版本号             描述
 */
package cn.xiaosm.plainadmin.service.impl;

import cn.xiaosm.plainadmin.entity.Log;
import cn.xiaosm.plainadmin.entity.ResponseEntity;
import cn.xiaosm.plainadmin.mapper.LogMapper;
import cn.xiaosm.plainadmin.service.LogService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 〈一句话功能简述〉
 * 〈〉
 *
 * @author Young
 * @create 2020/6/16
 * @since 1.0.0
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements LogService {

    @Override
    public ResponseEntity getById(Integer id) {
        return null;
    }

    @Override
    public boolean addEntity(Log log) {
        return false;
    }

    @Override
    public boolean removeEntity(Log log) {
        return false;
    }

    @Override
    public boolean modifyEntity(Log log) {
        return false;
    }

    @Override
    public Page<Log> listOfPage(Page<Log> page, QueryWrapper<Log> queryWrapper) {
        return this.page(page, queryWrapper);
    }
}