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
package cn.xiaosm.plainadmin.config.security.service;

import cn.hutool.core.util.ArrayUtil;
import cn.xiaosm.plainadmin.entity.LoginUser;
import cn.xiaosm.plainadmin.entity.Menu;
import cn.xiaosm.plainadmin.entity.Role;
import cn.xiaosm.plainadmin.entity.dto.UserDTO;
import cn.xiaosm.plainadmin.service.MenuService;
import cn.xiaosm.plainadmin.service.UserService;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;
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

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserDTO user = userService.getByUsername(username);
        if (Objects.isNull(user)) {
            throw new AuthenticationCredentialsNotFoundException("用户名输入错误");
        } else if (user.getStatus() == 0){
            throw new LockedException("用户已被禁用，请联系管理员");
        }

        // 设置用户id（字符串，已英文逗号分隔）
        user.setRoleIds(ArrayUtil.join(user.getRoles()
                .stream()
                .map(Role::getId)
                .toArray(), ","));
        // 通过roleIds 字符串添加用户所拥有的菜单<注意，这里还只是链表结构>
        user.setMenus(menuService.getByRoleIds(user.getRoleIds()));

        // 转变为 UserDetails 类型
        LoginUser loginUser = new LoginUser();
        BeanUtils.copyProperties(user, loginUser, "roles");

        // 设置登录用户的角色
        loginUser.setRoles(user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toList()));

        // 从菜单中获取并设置登录用户的权限
        Collection<SimpleGrantedAuthority> authorities =
                loginUser.getMenus().stream()
                        .filter(menu -> StringUtils.isNotBlank(menu.getPermission()))
                        .map(Menu::getPermission)
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());
        authorities.addAll(
                user.getRoles().stream()
                        .map(Role::getName)
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList())
        );
        loginUser.setAuthorities(authorities);
        // 构建登录用户菜单树
        loginUser.setMenus(menuService.buildTree(loginUser.getMenus(), 0));

        return loginUser;
    }

}