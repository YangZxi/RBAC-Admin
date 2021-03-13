/**
 * Copyright: 2019-2020，小树苗(www.xiaosm.cn)
 * FileName: LoginUser
 * Author:   Young
 * Date:     2020/6/16 20:56
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * Young         修改时间           版本号             描述
 */
package cn.xiaosm.yadmin.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * 〈一句话功能简述〉
 * 〈〉
 *
 * @author Young
 * @create 2020/6/16
 * @since 1.0.0
 */
public class LoginUser extends User implements UserDetails {

    @JsonIgnore
    private String password;
    private String roleIds;
    private List<Role> roles;
    private List<Menu> menus;
    private List<UserLoginTrack> userLoginTracks;
    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public LoginUser setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getRoleIds() {
        return roleIds;
    }

    public LoginUser setRoleIds(String roleIds) {
        this.roleIds = roleIds;
        return this;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public LoginUser setRoles(List<Role> roles) {
        this.roles = roles;
        return this;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public LoginUser setMenus(List<Menu> menus) {
        this.menus = menus;
        return this;
    }

    public List<UserLoginTrack> getUserLoginTracks() {
        return userLoginTracks;
    }

    public LoginUser setUserLoginTracks(List<UserLoginTrack> userLoginTracks) {
        this.userLoginTracks = userLoginTracks;
        return this;
    }

    public LoginUser setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
        return this;
    }
}