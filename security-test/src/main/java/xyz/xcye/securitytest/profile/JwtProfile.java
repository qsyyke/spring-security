package xyz.xcye.securitytest.profile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author ：qsyyke
 * @description：TODO
 * @date ：2022/3/12 09:09
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtProfile {
    /** jwt秘钥 创建jwt token和对token进行解密时，都需要 **/
    private String apiSecretKey = "JWT_AURORA_SECRET";

    /** token失效时间，单位s，默认为半小时 **/
    private Long expirationTime = 1800L;

    /** 在请求头中的head部分存放token的标识符/名称 **/
    private String requestHeader = "authorization_token";

    private String tokenPrefix = "Aurora ";
}
