package xyz.xcye.securityoauth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.yaml.snakeyaml.constructor.CustomClassLoaderConstructor;
import xyz.xcye.securityoauth2.service.CustomUserDetailsService;

/**
 * @author ：qsyyke
 * @description：配置授权服务器
 * @date ：2022/3/13 11:44
 */

@Configuration
@EnableAuthorizationServer
public class AuthorizationServer extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    /*@Autowired
    @Qualifier("redisTokenStore")
    private TokenStore tokenStore;*/

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                //自定义登录逻辑
                .userDetailsService(customUserDetailsService)
                .authenticationManager(authenticationManager);

                //设置令牌的存储位置
                //.tokenStore(tokenStore);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()

                //设置客户端id 正常是存放在数据库中的
                .withClient("admin")
                //设置客户端密码
                .secret(passwordEncoder.encode("123456"))
                //重定向地址，可以随便
                .redirectUris("https://aurora.xcye.xyz")
                //授权范围
                .scopes("all")
                //授权类型
                .authorizedGrantTypes("authorization_code","password");
    }
}
