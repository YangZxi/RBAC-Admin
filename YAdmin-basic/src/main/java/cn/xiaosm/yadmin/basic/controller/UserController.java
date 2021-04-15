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
package cn.xiaosm.yadmin.basic.controller;

import cn.hutool.core.util.StrUtil;
import cn.xiaosm.yadmin.basic.annotation.LogRecord;
import cn.xiaosm.yadmin.basic.config.security.service.TokenService;
import cn.xiaosm.yadmin.basic.entity.LoginUser;
import cn.xiaosm.yadmin.basic.entity.ResponseBody;
import cn.xiaosm.yadmin.basic.entity.User;
import cn.xiaosm.yadmin.basic.entity.vo.Pager;
import cn.xiaosm.yadmin.basic.entity.vo.UserVO;
import cn.xiaosm.yadmin.basic.exception.SQLOperateException;
import cn.xiaosm.yadmin.basic.service.UserService;
import cn.xiaosm.yadmin.basic.util.CacheUtils;
import cn.xiaosm.yadmin.basic.util.ResponseUtils;
import cn.xiaosm.yadmin.basic.util.WrapperUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 根据 Token 获取当前登录用户的信息
     * @param request
     * @return
     */
    @GetMapping("/info")
    public ResponseBody userInfo(HttpServletRequest request) {
        return ResponseUtils.buildSuccess(tokenService.getLoginUser(request));
    }

    /**
     * 修改个人信息
     * @param userVO
     * @param request
     * @return
     */
    @PostMapping("/info")
    public ResponseBody userInfo(@RequestBody UserVO userVO, HttpServletRequest request) {
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (userVO.getId() != loginUser.getId()) {
            return ResponseUtils.buildFail("修改失败");
        }
        if (StrUtil.isNotBlank(userVO.getPassword())) {
            if (!bCryptPasswordEncoder.matches(userVO.getPassword(), loginUser.getPassword())) {
                return ResponseUtils.buildFail("原密码错误");
            }
            userVO.setPassword(bCryptPasswordEncoder.encode(userVO.getNewPwd()));
        }
        // 用户更新
        if (userService.updateById(userVO)) {
            // 更新内存的对象
            BeanUtils.copyProperties(userVO, loginUser);
            CacheUtils.saveObject(loginUser.getUuid(), loginUser);
            return ResponseUtils.buildSuccess("个人资料修改成功");
        } else {
            return ResponseUtils.buildFail("修改失败");
        }
    }

    /**
     * 获取当前登录用户足迹
     * @param size
     * @return
     */
    @GetMapping("/info/track")
    public ResponseBody userTrack(@RequestParam(value = "size", defaultValue = "5") Integer size, HttpServletRequest request) {
        return ResponseUtils.buildSuccess(userService.listOfTrack(tokenService.getLoginUser(request).getId(), size));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('user:query') or hasRole('admin')")
    public ResponseBody queryUser(Pager<User> pager) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        WrapperUtils.bindSearch(wrapper, pager, "username");
        return ResponseUtils.buildSuccess("获取了用户列表", userService.page(pager, wrapper));
    }

    @PutMapping
    @LogRecord("添加用户")
    @PreAuthorize("(hasAuthority('user:add') and hasAuthority('role:query')) or hasRole('admin')")
    public ResponseBody saveUser(@RequestBody UserVO userVO) {
        boolean b = userService.addEntity(userVO);
        return b == true ? ResponseUtils.buildSuccess("新增用户信息成功")
                : ResponseUtils.buildFail("保存失败");
    }

    @PostMapping
    @LogRecord("修改用户")
    @PreAuthorize("hasAuthority('user:modify') or hasRole('admin')")
    public ResponseBody modifyUser(@RequestBody UserVO userVO) {
        if (userVO.getId() == 1) throw new SQLOperateException("系统保留数据，请勿操作");
        if (userVO.getIsReset()) userVO.setPassword("123456");
        boolean b = userService.modifyEntity(userVO);
        return b == true ? ResponseUtils.buildSuccess("修改用户信息成功")
                : ResponseUtils.buildFail("修改失败");
    }

    @DeleteMapping
    @LogRecord("删除用户")
    @PreAuthorize("hasAuthority('user:delete') or hasRole('admin')")
    public ResponseBody deleteUsers(@RequestBody Set<Integer> ids) {
        if (ids.stream().filter(el -> el == 1).count() == 1) {
            throw new SQLOperateException("系统保留数据，请勿操作");
        }
        int b = userService.removeByIds(ids);
        return ResponseUtils.buildSuccess("删除用户成功，本次删除" + b + "条用户");
    }

}