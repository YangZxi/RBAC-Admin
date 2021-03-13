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
package cn.xiaosm.yadmin.service.impl;

import cn.xiaosm.yadmin.entity.ResponseBody;
import cn.xiaosm.yadmin.entity.Task;
import cn.xiaosm.yadmin.entity.enums.StatusEnum;
import cn.xiaosm.yadmin.mapper.TaskMapper;
import cn.xiaosm.yadmin.scheduler.SchedulerService;
import cn.xiaosm.yadmin.service.TaskService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

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

    @Autowired
    TaskMapper taskMapper;
    @Autowired
    SchedulerService schedulerService;

    @Override
    public ResponseBody getById(Integer id) {
        return null;
    }

    @Override
    public boolean addEntity(Task task) {
        // 新建的任务默认是禁用状态
        task.setStatus(StatusEnum.DISABLED.getCode());
        task.setCreateTime(new Date());
        task.setUpdateTime(new Date());
        return this.save(task);
    }

    @Override
    public boolean removeEntity(Task task) {
        this.shutTask(task);
        task.setUpdateTime(new Date());
        task.setStatus(StatusEnum.DELETED.getCode());
        return taskMapper.updateById(task) == 1 ? true : false;
    }

    @Override
    public boolean modifyEntity(Task task) {
        if (task.getStatus() == 1) {
            task = this.getOne(new QueryWrapper<Task>().eq("id", task.getId()));
            // 如果状态为1开启任务
            this.startTask(task);
        } else if (task.getStatus() == 0) {
            task = this.getOne(new QueryWrapper<Task>().eq("id", task.getId()));
            this.shutTask(task);
        }
        return this.updateById(task);
    }

    @Override
    public Page<Task> listOfPage(Page<Task> page, QueryWrapper<Task> queryWrapper) {
        return null;
    }

    @Override
    public Page<Task> listOfPage(Page<Task> page, Task task) {
        QueryWrapper<Task> wrapper = new QueryWrapper<>();
        if (Objects.isNull(task.getStatus()) || task.getStatus() < 0) {
            wrapper.lt("status", 2);
        } else {
            wrapper.eq("status", task.getStatus());
        }
        return this.page(page, wrapper);
    }

    @Override
    public int removeByIds(Set<Integer> ids) {
        int count = 0;
        for (Integer id : ids) {
            count += this.removeById(id);
        }
        return count;
    }

    @Override
    public int removeById(Integer id) {
        return removeEntity(new Task().setId(id)) ? 1 : 0;
    }

    @Override
    public int startAllTask() {
        List<Task> tasks = this.list(new QueryWrapper<Task>().eq("status", 1));
        for (Task task : tasks) {
            this.startTask(task);
        }
        return tasks.size();
    }

    @Override
    public int shutAllTask() {
        schedulerService.shutdownJobs();
        return 1;
    }

    public boolean startTask(Task task) {
        String s = schedulerService.addJob(task);
        return true;
    }

    public boolean shutTask(Task task) {
        String s = schedulerService.removeJob(task);
        return true;
    }
}