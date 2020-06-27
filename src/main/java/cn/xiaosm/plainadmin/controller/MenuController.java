/**
 * Copyright: 2019-2020，小树苗(www.xiaosm.cn)
 * FileName: MenuController
 * Author:   Young
 * Date:     2020/6/18 13:42
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * Young         修改时间           版本号             描述
 */
package cn.xiaosm.plainadmin.controller;

import cn.xiaosm.plainadmin.annotation.LogRecord;
import cn.xiaosm.plainadmin.entity.ResponseBody;
import cn.xiaosm.plainadmin.entity.Menu;
import cn.xiaosm.plainadmin.entity.vo.MenuVO;
import cn.xiaosm.plainadmin.exception.SQLOperateException;
import cn.xiaosm.plainadmin.service.MenuService;
import cn.xiaosm.plainadmin.utils.ResponseUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 〈一句话功能简述〉
 * 〈〉
 *
 * @author Young
 * @create 2020/6/18
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api/menu")
@PreAuthorize("hasRole('admin')")
public class MenuController {

    @Autowired
    MenuService menuService;

    @GetMapping("")
    // @LogRecord("菜单查询")
    @PreAuthorize("hasAuthority('menu:query') or hasRole('admin')")
    public ResponseBody queryMenus(Page<Menu> page, MenuVO menu) {
        if ("tree".equals(menu.getShowType())) {
            // 默认父级菜单为0，不包含按钮
            return ResponseUtils.buildSuccess("成功获取菜单列表",
                    menuService.getByParentIdOfTree(menu.getParentMenu(), menu.isIncludeButton()));
        } else {
            QueryWrapper<Menu> wrapper = new QueryWrapper<Menu>();
            wrapper.eq("parent_menu", menu.getParentMenu());
            if (Objects.nonNull(menu.getStatus())) wrapper.eq("status", menu.getStatus());
            return ResponseUtils.buildSuccess("获取了菜单列表",
                    menuService.page(page, wrapper));
        }

    }

    @PutMapping
    @LogRecord("菜单添加")
    @PreAuthorize("hasAuthority('menu:add') or hasRole('admin')")
    public ResponseBody saveMenu(@RequestBody Menu menu) {
        boolean b = false;
        b = menuService.addEntity(menu);
        // 重新获取最新的菜单表到内存
        if (b) menuService.refreshMenus();
        return b == true ? ResponseUtils.buildSuccess("新增菜单信息成功")
                : ResponseUtils.buildFail("新增失败");
    }

    @PostMapping
    @LogRecord("菜单修改")
    @PreAuthorize("hasAuthority('menu:modify') or hasRole('admin')")
    public ResponseBody modifyMenu(@RequestBody Menu menu) {
        if (menu.getId() <= 36) {
            throw new SQLOperateException("系统保留数据，请勿操作");
        }
        menu.setUpdateTime(new Date());
        boolean b = menuService.updateById(menu);
        // 重新获取最新的菜单表到内存
        if (b) menuService.refreshMenus();
        return b == true ? ResponseUtils.buildSuccess("修改菜单信息成功")
                : ResponseUtils.buildFail("修改失败");
    }

    @DeleteMapping
    @LogRecord("菜单删除")
    @PreAuthorize("hasAuthority('menu:delete') or hasRole('admin')")
    public ResponseBody deleteMenus(@RequestBody Set<Integer> ids) {
        if (ids.stream().filter(el -> el <= 36).count() >= 1) {
            throw new SQLOperateException("系统保留数据，请勿操作");
        }
        int b = menuService.removeByIds(ids);
        return ResponseUtils.buildSuccess("删除菜单信息成功，本次删除" + b + "条菜单");
    }

}