package xyz.xcye.securitydemo02.config;

import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author ：qsyyke
 * @description：TODO
 * @date ：2022/3/11 18:42
 */

@Component(value = "userDetails")
public class MyUserDetails implements UserDetails {

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection grantedAuthority = Collections.singleton(new SimpleGrantedAuthority("admin,docs"));
        return grantedAuthority;
    }

    /**
     * 这里是根据用户名，返回密码
     * @return
     */
    @Override
    public String getPassword() {
        return "123456";
    }

    @Override
    public String getUsername() {
        return "aurora";
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
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
