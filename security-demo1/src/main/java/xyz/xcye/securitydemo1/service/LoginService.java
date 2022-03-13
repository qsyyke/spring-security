package xyz.xcye.securitydemo1.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import xyz.xcye.securitydemo1.entity.Users;
import xyz.xcye.securitydemo1.mapper.UserMapper;

import java.util.List;

/**
 * @author ：qsyyke
 * @description：TODO
 * @date ：2022/3/8 19:23
 */

//这是第三种方法，但是一定要保证密码是加密过的
@Service("userDetailsService")
public class LoginService implements UserDetailsService {

    /*@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("username: " + username);

        if (!"qsyyke".equals(username)) {
            throw new UsernameNotFoundException("用户名不存在");
        }

        //
        return new User(username, new BCryptPasswordEncoder().encode("1234"), AuthorityUtils.commaSeparatedStringToAuthorityList("aurora"));
    }*/

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<Users> wrapper = new QueryWrapper<>();

        wrapper.eq("username",username);

        Users user = userMapper.selectOne(wrapper);

        if (user == null) {
            //数据库中，没有该条记录，认证失败
            throw new UsernameNotFoundException("用户不存在");
        }

        List<GrantedAuthority> grantedAuthorities =
                AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRole());
        return new User(user.getUsername(),new BCryptPasswordEncoder().encode(user.getPassword()),grantedAuthorities);
    }
}
