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
import cn.xiaosm.plainadmin.entity.PageInfo;
import cn.xiaosm.plainadmin.entity.ResponseEntity;
import cn.xiaosm.plainadmin.service.UserService;
import cn.xiaosm.plainadmin.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
    @PreAuthorize("hasAuthority('find')")
    public ResponseEntity queryUsers(PageInfo pageInfo) {

        return ResponseUtils.buildSuccess("获取了用户列表");
    }

    @PutMapping
    @PreAuthorize("('user:add')")
    public ResponseEntity saveUser() {

        return ResponseUtils.buildSuccess("保存了用户信息");
    }

    @PostMapping
    @PreAuthorize("('user:modify')")
    public ResponseEntity modifyUser() {

        return ResponseUtils.buildSuccess("修改了用户信息");
    }

    @DeleteMapping
    @PreAuthorize("('user:delete')")
    public ResponseEntity deleteUsers() {

        return ResponseUtils.buildSuccess("删除了用户信息");
    }

}