package xyz.xcye.securitytest.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import xyz.xcye.securitytest.dao.UserMapper;
import xyz.xcye.securitytest.entity.AccessToken;
import xyz.xcye.securitytest.entity.MyUserDetails;
import xyz.xcye.securitytest.entity.ResultEntity;
import xyz.xcye.securitytest.entity.User;
import xyz.xcye.securitytest.profile.JwtProfile;
import xyz.xcye.securitytest.utils.JwtProvider;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ：qsyyke
 * @description：TODO
 * @date ：2022/3/12 11:20
 */
@RequestMapping("/user")
@Slf4j
@RestController
public class UserController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private JwtProfile jwtProfile;

    @GetMapping("/login")
    public ResultEntity login(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletResponse response) {
        log.info("进行登录操作");

        //创建一个未认证的对象
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username,password);

        //执行认证操作
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        //保存认证信息
        SecurityContextHolder.getContext().setAuthentication(authenticate);

        //生成自定义token
        AccessToken accessToken = jwtProvider.createToken(username);

        response.setHeader(jwtProfile.getRequestHeader(),accessToken.getToken());
        UserDetails userDetail = (MyUserDetails) authenticate.getPrincipal();

        return ResultEntity.builder().code(100).token(accessToken.getToken()).data(userDetail).build();
    }

    @GetMapping("/get")
    public ResultEntity getUser(String username) {
        User userByUsername = userMapper.getUserByUsername(username);
        ResultEntity<Object> build = ResultEntity.builder().code(100).token("").data(userByUsername).build();
        System.out.println(build);
        return build;
    }

    @GetMapping("/insert")
    public ResultEntity insert(String username,String password,String role) {

        String encodePassword = passwordEncoder.encode(new StringBuffer(password));

        int i = userMapper.insertUser(new User(4, username, encodePassword, role));

        ResultEntity<Object> build = ResultEntity.builder().code(100).token("").data(i).build();
        System.out.println(build);
        return build;
    }
}
