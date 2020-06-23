/**
 * Copyright: 2019-2020，小树苗(www.xiaosm.cn)
 * FileName: RoleController
 * Author:   Young
 * Date:     2020/6/13 14:25
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * Young         修改时间           版本号             描述
 */
package cn.xiaosm.plainadmin.controller;

import cn.xiaosm.plainadmin.entity.Menu;
import cn.xiaosm.plainadmin.entity.ResponseBody;
import cn.xiaosm.plainadmin.entity.Role;
import cn.xiaosm.plainadmin.entity.vo.RoleVO;
import cn.xiaosm.plainadmin.exception.SQLOperateException;
import cn.xiaosm.plainadmin.service.MenuService;
import cn.xiaosm.plainadmin.service.RoleService;
import cn.xiaosm.plainadmin.utils.ResponseUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 〈一句话功能简述〉
 * 〈〉
 *
 * @author Young
 * @create 2020/6/13
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api/role")
// @PreAuthorize("hasRole('admin')")
public class RoleController {

    @Autowired
    RoleService roleService;
    @Autowired
    MenuService menuService;

    @GetMapping("")
    @PreAuthorize("hasAuthority('role:query') or hasRole('admin')")
    public ResponseBody queryRoles(Page<Role> page, RoleVO roleVO) {
        // 如果用户id不为空，则查询当前用户的角色
        if (Objects.nonNull(roleVO.getUserId())) {
            return ResponseUtils.buildSuccess("",
                    roleService.getByUserId(roleVO.getUserId()).stream()
                    // 由于暂时仅需要角色的id
                    .map(Role::getId).collect(Collectors.toList())
            );
        } else {
            return ResponseUtils.buildSuccess("获取了角色列表",
                    roleService.page(page, null));
        }
    }

    @PutMapping
    @PreAuthorize("hasAuthority('role:add') or hasRole('admin')")
    public ResponseBody saveRole(@RequestBody Role role) {
        boolean b = roleService.save(role);
        return b == true ? ResponseUtils.buildSuccess("新增角色信息成功")
                : ResponseUtils.buildFail("保存失败");
    }

    @PostMapping
    @PreAuthorize("hasAuthority('role:modify') or hasRole('admin')")
    public ResponseBody modifyRole(@RequestBody RoleVO roleVO) {
        if (roleVO.getId() == 1) {
            throw new SQLOperateException("系统保留数据，请勿操作");
        }
        boolean b = roleService.modifyEntity(roleVO);
        if (b == Objects.nonNull(roleVO.getMenuIds())) {
            return ResponseUtils.buildSuccess("修改角色权限成功");
        }
        return b == true ? ResponseUtils.buildSuccess("修改角色信息成功")
                : ResponseUtils.buildFail("修改失败");
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('role:delete') or hasRole('admin')")
    public ResponseBody deleteRoles(@RequestBody Set<Integer> roleIds) {
        if (roleIds.stream().filter(el -> el == 1).count() == 1) {
            throw new SQLOperateException("系统保留数据，请勿操作");
        }
        boolean b = roleService.removeByIds(roleIds);
        return b == true ? ResponseUtils.buildSuccess("删除角色信息成功")
                : ResponseUtils.buildFail("删除角色失败");
    }


    /**
     * 根据角色id查询对应的菜单
     * @param roleId
     * @return
     */
    @GetMapping("/menu")
    @PreAuthorize("(hasAnyAuthority('role:query') and hasAuthority('menu:query')) or hasRole('admin')")
    public ResponseBody queryMenuByRoleId(@RequestParam Integer roleId) {
        List<Integer> list = menuService.getByRoleIds(String.valueOf(roleId))
                .stream().map(Menu::getId).collect(Collectors.toList());
        return ResponseUtils.buildSuccess(list);
    }
}