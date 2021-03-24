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
package cn.xiaosm.yadmin.basic.controller;

import cn.hutool.core.util.StrUtil;
import cn.xiaosm.yadmin.basic.annotation.LogRecord;
import cn.xiaosm.yadmin.basic.entity.Log;
import cn.xiaosm.yadmin.basic.entity.ResponseBody;
import cn.xiaosm.yadmin.basic.entity.vo.LogVO;
import cn.xiaosm.yadmin.basic.entity.vo.Pager;
import cn.xiaosm.yadmin.basic.service.LogService;
import cn.xiaosm.yadmin.basic.util.ResponseUtils;
import cn.xiaosm.yadmin.basic.util.WrapperUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    public ResponseBody queryLogs(Pager<Log> pager, LogVO logVO) {
        QueryWrapper<Log> wrapper = new QueryWrapper();
        WrapperUtils.bindSearch(wrapper, pager);
        if (StrUtil.isNotBlank(pager.getWord())) {
            wrapper.like("title", pager.getWord());
        }
        wrapper.eq("type", logVO.getType())
                .orderByDesc("create_time");
        Page<Log> list = logService.page(pager, wrapper);
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