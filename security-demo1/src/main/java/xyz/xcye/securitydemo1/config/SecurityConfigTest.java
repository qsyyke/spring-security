package xyz.xcye.securitydemo1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
public class SecurityConfigTest extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    //注入数据源
    @Autowired
    private DataSource dataSource;
    //配置对象

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();

        //赋值数据源
        jdbcTokenRepository.setDataSource(dataSource);

        //自动创建表，第一次执行的时候，会创建，以后要执行，就删除
        jdbcTokenRepository.setCreateTableOnStartup(true);

        return jdbcTokenRepository;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(password());
    }

    @Bean
    PasswordEncoder password() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        //开启记住我功能
        httpSecurity.rememberMe().tokenRepository(persistentTokenRepository())
                .userDetailsService(userDetailsService).tokenValiditySeconds(300);//设置有效时间

        //配置没有权限访问的页面
        httpSecurity.exceptionHandling().accessDeniedPage("/unauth.html");

        //自己编写登录页面
        httpSecurity.formLogin().
                loginPage("/login.html").//用户登录页面
                loginProcessingUrl("/user/login")//登录访问路径
                .defaultSuccessUrl("/sec/success").permitAll()//登录成功之后，跳转路径

                .and().authorizeRequests().
                antMatchers("/","/sec/hello","/user/login")//该部分的地址，访问不需要验证
                .permitAll()
//                .antMatchers("/sec/admin").hasAuthority("admins")
                .antMatchers("/sec/admin").hasAnyAuthority("admin,manager")

                //上面这两个都是基于用户的访问

//                .antMatchers("/sec/admin").hasRole("sale")
                .anyRequest().authenticated().and().csrf().disable();//关闭csrf防护
    }
}
