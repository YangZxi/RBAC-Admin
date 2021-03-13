/**
 * Copyright: 2019-2020
 * FileName: TestQuartz
 * Author:   Young
 * Date:     2020/6/28 9:21
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * Young         修改时间           版本号             描述
 */
package cn.xiaosm.plainadmin;

import cn.xiaosm.yadmin.entity.Task;
import cn.xiaosm.yadmin.scheduler.SchedulerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 〈一句话功能简述〉
 * 〈〉
 *
 * @author Young
 * @create 2020/6/28
 * @since 1.0.0
 */
@SpringBootTest
public class TestQuartz {

    @Autowired
    SchedulerService quartzService;

    @Test
    public void Test1() {
        Task task = new Task();
        task.setClassName("cn.xiaosm.plainadmin.scheduler.EmailTask");
        task.setName("test");
        task.setCron("0/5 0 0 * * ?");
        quartzService.addJob(task);
    }

}