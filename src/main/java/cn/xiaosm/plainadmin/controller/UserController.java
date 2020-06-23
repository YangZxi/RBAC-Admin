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
import cn.xiaosm.plainadmin.entity.ResponseBody;
import cn.xiaosm.plainadmin.entity.User;
import cn.xiaosm.plainadmin.entity.enums.StatusEnum;
import cn.xiaosm.plainadmin.entity.vo.UserVO;
import cn.xiaosm.plainadmin.exception.SQLOperateException;
import cn.xiaosm.plainadmin.service.UserService;
import cn.xiaosm.plainadmin.utils.ResponseUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

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


    /**
     * 根据 Token 获取当前登录用户的信息
     * @param request
     * @return
     */
    @GetMapping("/info")
    public ResponseBody userInfo(HttpServletRequest request) {
        return ResponseUtils.buildSuccess(tokenService.getLoginUser(request));
    }

    @GetMapping("")
    @PreAuthorize("hasAuthority('user:query') or hasRole('admin')")
    public ResponseBody queryUsers(Page<User> page, UserVO userVO) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        // 不查询已删除的用户
        wrapper.ne("status", StatusEnum.DELETED.getCode());
        Page<User> list = userService.page(page, wrapper);
        return ResponseUtils.buildSuccess("获取了用户列表", list);
    }

    @PutMapping
    @PreAuthorize("(hasAuthority('user:add') and hasAuthority('role:query')) or hasRole('admin')")
    public ResponseBody saveUser(@RequestBody UserVO userVO) {
        boolean b = userService.addEntity(userVO);
        return b == true ? ResponseUtils.buildSuccess("新增用户信息成功")
                : ResponseUtils.buildFail("保存失败");
    }

    @PostMapping
    @PreAuthorize("hasAuthority('user:modify') or hasRole('admin')")
    public ResponseBody modifyUser(@RequestBody UserVO userVO) {
        if (userVO.getId() == 1) throw new SQLOperateException("系统保留数据，请勿操作");
        boolean b = userService.modifyEntity(userVO);
        return b == true ? ResponseUtils.buildSuccess("修改用户信息成功")
                : ResponseUtils.buildFail("修改失败");
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('user:delete') or hasRole('admin')")
    public ResponseBody deleteUsers(@RequestBody Set<Integer> ids) {
        if (ids.stream().filter(el -> el == 1).count() == 1) {
            throw new SQLOperateException("系统保留数据，请勿操作");
        }
        int b = userService.removeByIds(ids);
        return ResponseUtils.buildSuccess("删除用户成功，本次删除" + b + "条用户");
    }

}