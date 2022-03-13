package xyz.xcye.securityoauth2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import xyz.xcye.securityoauth2.handler.CustomAccessDeniedHandler;

/**
 * @author ：qsyyke
 * @description：TODO
 * @date ：2022/3/12 18:51
 */

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //禁止csrf()
        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers("/ouath/**","/login/**","/logout/**").permitAll()
                .anyRequest().authenticated();

        http.formLogin()
                .loginProcessingUrl("/login")
                .successForwardUrl("/login/success")
                .loginPage("/login.html")
                .permitAll();

        //自定义异常处理
        http.exceptionHandling()
                .accessDeniedHandler(new CustomAccessDeniedHandler());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
