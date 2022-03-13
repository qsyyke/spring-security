package xyz.xcye.securitytest.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author ：qsyyke
 * @description：这个类，是UserDetails的一个实现类，我们需要在这里面
 * @date ：2022/3/12 12:24
 */


public class MyUserDetails implements UserDetails {

    public MyUserDetails(User user) {
        this.user = user;
    }

    private User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();

        String[] split = user.getRole().split(",");
        for (String role : split) {
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        }
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
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
