/**
 * Copyright: 2019-2020，小树苗(www.xiaosm.cn)
 * FileName: TaskController
 * Author:   Young
 * Date:     2020/6/18 13:42
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * Young         修改时间           版本号             描述
 */
package cn.xiaosm.plainadmin.controller;

import cn.xiaosm.plainadmin.entity.Task;
import cn.xiaosm.plainadmin.entity.ResponseEntity;
import cn.xiaosm.plainadmin.service.TaskService;
import cn.xiaosm.plainadmin.utils.ResponseUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 〈一句话功能简述〉
 * 〈〉
 *
 * @author Young
 * @create 2020/6/18
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    TaskService taskService;

    @GetMapping("")
    @PreAuthorize("hasAuthority('task:query')")
    public ResponseEntity queryTasks(Page<Task> page) {
        Page<Task> list = taskService.page(page, null);
        return ResponseUtils.buildSuccess("获取了任务列表", list);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('task:add')")
    public ResponseEntity saveTask(@RequestBody Task task) {
        boolean b = taskService.save(task);
        return b == true ? ResponseUtils.buildSuccess("新增任务信息成功")
                : ResponseUtils.buildFail("保存失败");
    }

    @PostMapping
    @PreAuthorize("hasAuthority('task:modify')")
    public ResponseEntity modifyTask(@RequestBody Task task) {
        boolean b = taskService.updateById(task);
        return b == true ? ResponseUtils.buildSuccess("修改任务信息成功")
                : ResponseUtils.buildFail("修改失败");
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('task:delete')")
    public ResponseEntity deleteTasks(List<Integer> ids) {
        boolean b = taskService.removeByIds(ids);
        return b == true ? ResponseUtils.buildSuccess("删除任务信息成功")
                : ResponseUtils.buildFail("删除任务失败");
    }
}