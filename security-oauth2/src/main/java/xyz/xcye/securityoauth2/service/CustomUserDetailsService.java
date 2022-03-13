package xyz.xcye.securityoauth2.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author ：qsyyke
 * @description：TODO
 * @date ：2022/3/12 18:53
 */

@Slf4j
@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        String encodePassword = passwordEncoder.encode("aurora");
        log.info(encodePassword);
        return new User("aurora",encodePassword,
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin,ROLE_man"));
    }
}
