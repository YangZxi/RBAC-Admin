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

import cn.xiaosm.plainadmin.config.security.service.TokenService;
import cn.xiaosm.plainadmin.entity.ResponseEntity;
import cn.xiaosm.plainadmin.entity.Role;
import cn.xiaosm.plainadmin.service.RoleService;
import cn.xiaosm.plainadmin.utils.ResponseUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
@PreAuthorize("hasRole('admin')")
public class RoleController {

    @Autowired
    RoleService roleService;

    @GetMapping("")
    @PreAuthorize("hasAuthority('role:query')")
    public ResponseEntity queryRoles(Page<Role> page) {
        Page<Role> list = roleService.page(page, null);
        return ResponseUtils.buildSuccess("获取了角色列表", list);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('role:add')")
    public ResponseEntity saveRole(@RequestBody Role role) {
        boolean b = roleService.save(role);
        return b == true ? ResponseUtils.buildSuccess("新增角色信息成功")
                : ResponseUtils.buildFail("保存失败");
    }

    @PostMapping
    @PreAuthorize("hasAuthority('role:modify')")
    public ResponseEntity modifyRole(@RequestBody Role role) {
        boolean b = roleService.updateById(role);
        return b == true ? ResponseUtils.buildSuccess("修改角色信息成功")
                : ResponseUtils.buildFail("修改失败");
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('role:delete')")
    public ResponseEntity deleteRoles(List<Integer> ids) {
        boolean b = roleService.removeByIds(ids);
        return b == true ? ResponseUtils.buildSuccess("删除角色信息成功")
                : ResponseUtils.buildFail("删除角色失败");
    }

}