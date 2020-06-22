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

import cn.xiaosm.plainadmin.entity.ResponseEntity;
import cn.xiaosm.plainadmin.entity.Menu;
import cn.xiaosm.plainadmin.entity.vo.MenuVO;
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
    @PreAuthorize("hasAuthority('menu:query') or hasRole('admin')")
    public ResponseEntity queryMenus(Page<Menu> page, MenuVO menu) {
        if ("tree".equals(menu.getShowType())) {
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
    @PreAuthorize("hasAuthority('menu:add') or hasRole('admin')")
    public ResponseEntity saveMenu(@RequestBody Menu menu) {
        boolean b = false;
        b = menuService.addEntity(menu);
        // 重新获取最新的菜单表到内存
        if (b) menuService.refreshMenus();
        return b == true ? ResponseUtils.buildSuccess("新增菜单信息成功")
                : ResponseUtils.buildFail("新增失败");
    }

    @PostMapping
    @PreAuthorize("hasAuthority('menu:modify') or hasRole('admin')")
    public ResponseEntity modifyMenu(@RequestBody Menu menu) {
        menu.setUpdateTime(new Date());
        boolean b = menuService.updateById(menu);
        // 重新获取最新的菜单表到内存
        if (b) menuService.refreshMenus();
        return b == true ? ResponseUtils.buildSuccess("修改菜单信息成功")
                : ResponseUtils.buildFail("修改失败");
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('menu:delete') or hasRole('admin')")
    public ResponseEntity deleteMenus(@RequestBody Set<Integer> ids) {
        int b = menuService.removeByIds(ids);
        return ResponseUtils.buildSuccess("删除菜单信息成功，本次删除" + b + "条菜单");
    }

}