/**
 * Copyright: 2019-2020，小树苗(www.xiaosm.cn)
 * FileName: UserDetailsServiceImpl
 * Author:   Young
 * Date:     2020/6/15 15:44
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * Young         修改时间           版本号             描述
 */
package cn.xiaosm.yadmin.basic.config.security.service;

import cn.hutool.core.util.ArrayUtil;
import cn.xiaosm.yadmin.basic.entity.LoginUser;
import cn.xiaosm.yadmin.basic.entity.Menu;
import cn.xiaosm.yadmin.basic.entity.Role;
import cn.xiaosm.yadmin.basic.entity.dto.UserDTO;
import cn.xiaosm.yadmin.basic.service.MenuService;
import cn.xiaosm.yadmin.basic.service.UserService;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

/**
 * 〈一句话功能简述〉
 * 〈〉
 *
 * @author Young
 * @create 2020/6/15
 * @since 1.0.0
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserService userService;
    @Autowired
    MenuService menuService;

    /**
     * 登录
     * @param username
     * @return
     */
    @Override
    public UserDetails loadUserByUsername(String username) {
        // 获取 userDTO 信息
        UserDTO userDTO = userService.getByUsername(username);
        // 验证用户状态
        this.validateUser(userDTO);
        // 转变为 UserDetails 类型
        LoginUser loginUser = new LoginUser();
        BeanUtils.copyProperties(userDTO, loginUser, "");
        return loginUser;
    }

    /**
     * 通过快捷登录方式登录系统
     * @param openId
     * @param source
     * @return
     */
    public UserDetails loadUserByOpenId(String openId, String source) {
        UserDTO userDTO = null;
        if ("qq".equals(source)) {
            userDTO = userService.getByUsername(openId, "qq_id");
        }
        if (Objects.isNull(userDTO)) return null;
        // 验证用户状态
        this.validateUser(userDTO);// 转变为 UserDetails 类型
        LoginUser loginUser = new LoginUser();
        BeanUtils.copyProperties(userDTO, loginUser, "");
        return loginUser;
    }

    /**
     * 加载用户信息，包括用户的所有角色和角色所拥有的权限
     *
     * @param loginUser
     */
    public void loadUserInfo(LoginUser loginUser) {
        // 是否管理员
        AtomicBoolean isAdmin = new AtomicBoolean(false);
        // 设置用户id（字符串，以英文逗号分隔），并判断是否为管理员
        loginUser.setRoleIds(ArrayUtil.join(loginUser.getRoles()
                .stream()
                .map(el -> {
                    // 如果是管理员则查出所有的菜单
                    if ("ROLE_admin".equals(el.getName())) isAdmin.set(true);
                    return el.getId();
                })
                .toArray(), ","));
        // 通过roleIds 字符串添加用户所拥有的菜单<注意，这里还只是链表结构>
        if (isAdmin.get() == true) {
            loginUser.setMenusOriginal(menuService.getAll(true));
        } else {
            loginUser.setMenusOriginal(menuService.getByRoleIds(loginUser.getRoleIds()));
        }

        // 从菜单中获取并设置登录用户的权限
        // 设置菜单权限
        Collection<SimpleGrantedAuthority> authorities =
                loginUser.getMenusOriginalOfList().stream()
                    // 取出权限字段（permission）不为空的值
                    .filter(menu -> StringUtils.isNotBlank(menu.getPermission()))
                    .map(Menu::getPermission)
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
        // 设置角色权限
        authorities.addAll(
            loginUser.getRoles().stream()
                // 取出角色名
                .map(Role::getName)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList())
        );
        loginUser.setAuthorities(authorities);
        // 构建登录用户菜单树，在首页展示导航栏需要用到
        loginUser.setMenus(menuService.buildTree(loginUser.getMenusOriginalOfList()));
    }

    private void validateUser(UserDTO user) {
        if (Objects.isNull(user)) {
            throw new AuthenticationCredentialsNotFoundException("用户名输入错误");
        } else if (user.getStatus() == 0){
            throw new LockedException("用户已被禁用，请联系管理员");
        } else if (user.getStatus() == 2) {
            throw new DisabledException("用户状态异常，请联系管理员");
        }
    }

}