package xyz.xcye.securitytest.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @author ：qsyyke
 * @description：根据用户传入的用户名，toekn，失效时间，构建一个账户对象
 * @date ：2022/3/12 09:28
 */

@Builder
@Data
public class AccessToken {
    /** 登录用户名 **/
    private String loginAccount;

    /** token **/
    private String token;

    /** 失效时间 **/
    private Date expirationTime;

}
