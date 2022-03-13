package xyz.xcye.securitydemo02.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author ：qsyyke
 * @description：TODO
 * @date ：2022/3/11 13:46
 */

@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDetails userDetails;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new MyUserDetails();
    }
}
