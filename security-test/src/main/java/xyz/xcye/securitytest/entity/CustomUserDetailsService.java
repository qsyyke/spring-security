package xyz.xcye.securitytest.entity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import xyz.xcye.securitytest.dao.UserMapper;

/**
 * @author ：qsyyke
 * @description：TODO
 * @date ：2022/3/12 12:32
 */

@Slf4j
@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 在这个方法中，返回一个包含正确的用户名和密码的UserDetails对象
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("开始获取正确用户名，密码对象: {}",username);
        //从数据库中查询User
        User user = userMapper.getUserByUsername(username);
        if (user == null) {
            log.error("此用户不存在 {}",username);
            throw new UsernameNotFoundException("用户不存在");
        }
        //用户名存在，则返回包含正确用户名和密码的UserDetails对象
        return new MyUserDetails(user);
    }
}
