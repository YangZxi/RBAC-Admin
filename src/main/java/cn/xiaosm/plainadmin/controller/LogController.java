/**
 * Copyright: 2019-2020，小树苗(www.xiaosm.cn)
 * FileName: LogController
 * Author:   Young
 * Date:     2020/6/18 13:42
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * Young         修改时间           版本号             描述
 */
package cn.xiaosm.plainadmin.controller;

import cn.xiaosm.plainadmin.annotation.LogRecord;
import cn.xiaosm.plainadmin.entity.Log;
import cn.xiaosm.plainadmin.entity.ResponseBody;
import cn.xiaosm.plainadmin.entity.vo.LogVO;
import cn.xiaosm.plainadmin.service.LogService;
import cn.xiaosm.plainadmin.utils.ResponseUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
@RestController
@RequestMapping("/api/log")
public class LogController {

    @Autowired
    LogService logService;

    @GetMapping
    @PreAuthorize("hasAuthority('log:query') or hasRole('admin')")
    public ResponseBody queryLogs(Page<Log> page, LogVO logVO) {
        Page<Log> list = logService.page(page,
                new QueryWrapper<Log>()
                    .eq("type", logVO.getType())
                    .orderByDesc("create_time")
        );
        return ResponseUtils.buildSuccess("获取了日志列表", list);
    }

    @DeleteMapping
    @LogRecord("删除日志")
    @PreAuthorize("hasAuthority('log:delete') or hasRole('admin')")
    public ResponseBody deleteLogs(@RequestBody Set<Integer> ids) {
        boolean b = logService.removeByIds(ids);
        return b == true ? ResponseUtils.buildSuccess("删除日志信息成功")
                : ResponseUtils.buildFail("删除日志失败");
    }
}