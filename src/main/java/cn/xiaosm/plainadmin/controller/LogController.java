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

import cn.xiaosm.plainadmin.entity.Log;
import cn.xiaosm.plainadmin.entity.ResponseBody;
import cn.xiaosm.plainadmin.service.LogService;
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
@RequestMapping("/api/log")
public class LogController {

    @Autowired
    LogService logService;

    @GetMapping("")
    @PreAuthorize("hasAuthority('log:query')")
    public ResponseBody queryLogs(Page<Log> page) {
        Page<Log> list = logService.page(page, null);
        return ResponseUtils.buildSuccess("获取了日志列表", list);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('log:add')")
    public ResponseBody saveLog(@RequestBody Log log) {
        boolean b = logService.save(log);
        return b == true ? ResponseUtils.buildSuccess("新增日志信息成功")
                : ResponseUtils.buildFail("保存失败");
    }

    @PostMapping
    @PreAuthorize("hasAuthority('log:modify')")
    public ResponseBody modifyLog(@RequestBody Log log) {
        boolean b = logService.updateById(log);
        return b == true ? ResponseUtils.buildSuccess("修改日志信息成功")
                : ResponseUtils.buildFail("修改失败");
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('log:delete')")
    public ResponseBody deleteLogs(List<Integer> ids) {
        boolean b = logService.removeByIds(ids);
        return b == true ? ResponseUtils.buildSuccess("删除日志信息成功")
                : ResponseUtils.buildFail("删除日志失败");
    }
}