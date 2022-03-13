package xyz.xcye.securitydemo03.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import xyz.xcye.securitydemo03.handler.CustomAccess;
import xyz.xcye.securitydemo03.handler.CustomAccessDeniedHandler;
import xyz.xcye.securitydemo03.handler.CustomAuthenticationSuccessHandler;
import xyz.xcye.securitydemo03.handler.CustomRememberMeServices;
import xyz.xcye.securitydemo03.service.CustomUserDetailsService;

import javax.sql.DataSource;

/**
 * @author ：qsyyke
 * @description：TODO
 * @date ：2022/3/12 18:51
 */

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    CustomAccess customAccess = new CustomAccess();

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //自定义登录页面
        http.formLogin()
                //自定义登录逻辑
                .loginProcessingUrl("/login")
//                .successForwardUrl("/loginSuccess")
                .successHandler(new CustomAuthenticationSuccessHandler())
                //.failureHandler()

                //设置登录页面
                .loginPage("/login.html");

        //授权
        http.authorizeRequests()
                //放行登录页面
                .antMatchers("/login.html").permitAll()

                //权限控制
                //.antMatchers("/main.html").hasAuthority("adsmin")
                //.antMatchers("/main.html").hasAnyAuthority("admin","sdlfkj")

                //.antMatchers("/main.html").hasAnyRole("ROLE_man")
                //.antMatchers("/main.html").hasRole("ROLE_man")
                //.antMatchers("/main.html").access("hasRole(\"men\")")
                //.antMatchers("/main.html").hasIpAddress("127.0.0.1")
                //.antMatchers("/main.html").hasIpAddress("127.0.0.1")
                //.antMatchers("/main.html").access("@customAccess.hasPermission(request,authentication)")
                //.antMatchers("/main.html").access("@customAccess.hasPermission(\"sdf\")")


                //所有请求都需要认证
                .anyRequest().authenticated();

        http.rememberMe()
                //设置多长时间过期
                //.tokenValiditySeconds()

                //自定义记住我逻辑实现
                .rememberMeServices(new CustomRememberMeServices())
                //自定义登录逻辑
                .userDetailsService(new CustomUserDetailsService())
                //自定义存储方式
                .tokenRepository(persistentTokenRepository());


        //禁止csrf()
        http.csrf().disable();

        //自定义异常处理
        http.exceptionHandling()
                .accessDeniedHandler(new CustomAccessDeniedHandler());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private DataSource dataSource;

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();

        //设置数据源
        jdbcTokenRepository.setDataSource(dataSource);

        //启动时是否创建表 第一次要
        //jdbcTokenRepository.setCreateTableOnStartup(true);
        return jdbcTokenRepository;
    }
}
