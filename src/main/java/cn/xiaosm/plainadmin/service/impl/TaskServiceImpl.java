/**
 * Copyright: 2019-2020，小树苗(www.xiaosm.cn)
 * FileName: TaskServiceImpl
 * Author:   Young
 * Date:     2020/6/18 16:02
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * Young         修改时间           版本号             描述
 */
package cn.xiaosm.plainadmin.service.impl;

import cn.xiaosm.plainadmin.entity.ResponseEntity;
import cn.xiaosm.plainadmin.entity.Task;
import cn.xiaosm.plainadmin.mapper.TaskMapper;
import cn.xiaosm.plainadmin.service.TaskService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 〈一句话功能简述〉
 * 〈〉
 *
 * @author Young
 * @create 2020/6/18
 * @since 1.0.0
 */
@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements TaskService {

    @Override
    public ResponseEntity getById(Integer id) {
        return null;
    }

    @Override
    public boolean addEntity(Task task) {
        return false;
    }

    @Override
    public boolean removeEntity(Task task) {
        return false;
    }

    @Override
    public boolean modifyEntity(Task task) {
        return false;
    }

    @Override
    public Page<Task> listOfPage(Page<Task> page, QueryWrapper<Task> queryWrapper) {
        return null;
    }
}