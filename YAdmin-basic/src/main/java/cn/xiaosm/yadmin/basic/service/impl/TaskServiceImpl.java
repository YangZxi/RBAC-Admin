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
package cn.xiaosm.yadmin.basic.service.impl;

import cn.xiaosm.yadmin.basic.entity.ResponseBody;
import cn.xiaosm.yadmin.basic.entity.Task;
import cn.xiaosm.yadmin.basic.entity.enums.StatusEnum;
import cn.xiaosm.yadmin.basic.entity.vo.Pager;
import cn.xiaosm.yadmin.basic.exception.CanShowException;
import cn.xiaosm.yadmin.basic.mapper.TaskMapper;
import cn.xiaosm.yadmin.basic.scheduler.SchedulerService;
import cn.xiaosm.yadmin.basic.service.TaskService;
import cn.xiaosm.yadmin.basic.util.WrapperUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
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
        task.setStatus(StatusEnum.DISABLED);
        return taskMapper.insert(task) == 1;
    }

    @Override
    public boolean removeEntity(Task task) {
        this.shutTask(task);
        task.setStatus(StatusEnum.DELETED);
        return taskMapper.updateById(task) == 1;
    }

    @Override
    @Transactional
    public boolean modifyEntity(Task task) {
        int n = taskMapper.updateById(task);
        if (task.getStatus() == StatusEnum.ENABLED) {
            task = this.getOne(new QueryWrapper<Task>().eq("id", task.getId()));
            // 如果状态为开启则启动任务
            this.startTask(task);
        } else {
            task = this.getOne(new QueryWrapper<Task>().eq("id", task.getId()));
            this.shutTask(task);
        }
        return n == 1;
    }

    @Override
    public Pager<Task> listOfPage(Pager<Task> pager, QueryWrapper<Task> queryWrapper) {
        return null;
    }

    @Override
    public Page<Task> listOfPage(Page<Task> page, Task task) {
        QueryWrapper<Task> wrapper = new QueryWrapper<>();
        WrapperUtils.bindSearch(wrapper,(Pager) page, "name", "`desc`")
            .orderByDesc("create_time");
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

    /**
     * 启动所有任务
     * @return 启用任务的条数
     */
    @Override
    public int startAllTask() {
        List<Task> tasks = this.list(new QueryWrapper<Task>().eq("status", StatusEnum.ENABLED));
        for (Task task : tasks) {
            this.startTask(task);
        }
        return tasks.size();
    }

    @Override
    public int shutAllTask() {
        return schedulerService.shutdownJobs();
    }

    public boolean startTask(Task task) {
        try {
            String s = schedulerService.addJob(task);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new CanShowException("启动任务失败",  e.getMessage() + "不存在");
        } catch (Exception e) {
            e.printStackTrace();
            throw new CanShowException("创建任务失败");
        }
        return true;
    }

    public boolean shutTask(Task task) {
        String s = schedulerService.removeJob(task);
        return true;
    }
}