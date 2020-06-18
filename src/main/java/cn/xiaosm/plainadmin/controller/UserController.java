/**
 * Copyright: 2019-2020，小树苗(www.xiaosm.cn)
 * FileName: UserController
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
import cn.xiaosm.plainadmin.entity.User;
import cn.xiaosm.plainadmin.service.UserService;
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
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    TokenService tokenService;

    // @GetMapping("/{id}")
    // @PreAuthorize("('user:get')")
    // public ResponseEntity getUsers(@PathVariable int id) {
    //
    //     return ResponseUtils.sendSuccess("获取了单个用户");
    // }


    @GetMapping("/info")
    public ResponseEntity userInfo(HttpServletRequest request) {
        return ResponseUtils.buildSuccess(tokenService.getLoginUser(request));
    }

    @GetMapping("")
    @PreAuthorize("hasAuthority('user:query')")
    public ResponseEntity queryUsers(Page<User> page) {
        Page<User> list = userService.page(page, null);
        return ResponseUtils.buildSuccess("获取了用户列表", list);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('user:add')")
    public ResponseEntity saveUser(@RequestBody User user) {
        boolean b = userService.save(user);
        return b == true ? ResponseUtils.buildSuccess("新增用户信息成功")
                : ResponseUtils.buildFail("保存失败");
    }

    @PostMapping
    @PreAuthorize("hasAuthority('user:modify')")
    public ResponseEntity modifyUser(@RequestBody User user) {
        boolean b = userService.updateById(user);
        return b == true ? ResponseUtils.buildSuccess("修改用户信息成功")
                : ResponseUtils.buildFail("修改失败");
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('user:delete')")
    public ResponseEntity deleteUsers(List<Integer> ids) {
        boolean b = userService.removeByIds(ids);
        return b == true ? ResponseUtils.buildSuccess("删除用户信息成功")
                : ResponseUtils.buildFail("删除用户失败");
    }

}