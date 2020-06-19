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
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

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
public class MenuController {

    @Autowired
    MenuService menuService;

    @GetMapping("")
    @PreAuthorize("hasAuthority('menu:query') or hasRole('admin')")
    public ResponseEntity queryMenus(Page<Menu> page, MenuVO menu) {
        if ("tree".equals(menu.getShowType())) {
            return ResponseUtils.buildSuccess("成功获取菜单列表",
                    menuService.getByParentIdOfTree(menu.getParentMenu()));
        } else {
            QueryWrapper<Menu> wrapper = new QueryWrapper<>();
            wrapper.eq("parent_menu", menu.getParentMenu());
            return ResponseUtils.buildSuccess("获取了菜单列表",
                    menuService.page(page, wrapper));
        }

    }

    @PutMapping
    @PreAuthorize("hasAuthority('menu:add')")
    public ResponseEntity saveMenu(@RequestBody Menu menu) {
        boolean b = menuService.save(menu);
        return b == true ? ResponseUtils.buildSuccess("新增菜单信息成功")
                : ResponseUtils.buildFail("保存失败");
    }

    @PostMapping
    @PreAuthorize("hasAuthority('menu:modify')")
    public ResponseEntity modifyMenu(@RequestBody Menu menu) {
        boolean b = menuService.updateById(menu);
        return b == true ? ResponseUtils.buildSuccess("修改菜单信息成功")
                : ResponseUtils.buildFail("修改失败");
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('menu:delete')")
    public ResponseEntity deleteMenus(List<Integer> ids) {
        boolean b = menuService.removeByIds(ids);
        return b == true ? ResponseUtils.buildSuccess("删除菜单信息成功")
                : ResponseUtils.buildFail("删除菜单失败");
    }

}