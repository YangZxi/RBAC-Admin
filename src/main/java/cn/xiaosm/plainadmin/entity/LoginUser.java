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
package cn.xiaosm.plainadmin.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
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
@Data
public class LoginUser extends User implements Serializable, UserDetails {

    @JsonIgnore
    private String password;
    private List<String> roles;
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
}