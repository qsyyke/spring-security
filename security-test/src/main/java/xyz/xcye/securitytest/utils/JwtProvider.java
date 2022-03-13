package xyz.xcye.securitytest.utils;

import cn.hutool.core.date.DateUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import xyz.xcye.securitytest.entity.AccessToken;
import xyz.xcye.securitytest.profile.JwtProfile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author ：qsyyke
 * @description ：这是一个生成jwt的工具类，可以对token进行解密等
 * @date ：2022/3/12 09:22
 */

@Slf4j
@Component
public class JwtProvider {

    @Resource
    private JwtProfile jwtProfile;

    /**
     * 从请求中获取到token
     * @param request
     * @return
     */
    public String getTokenByRequest(HttpServletRequest request) {
        String requestToken = request.getHeader(jwtProfile.getRequestHeader());
        if (requestToken != null) {
            return requestToken;
        }

        return null;
    }

    /**
     * 根据UserDetails对象中的用户名，生成token
     * @param userDetails 存放用户数据
     * @return
     */
    public AccessToken createToken(UserDetails userDetails) {
        return createToken(userDetails.getUsername());
    }

    /**
     * 根据传入的用户名，生成一个token
     * @param subject 用户名
     * @return
     */
    public AccessToken createToken(String subject) {
        //当前的时间 使用final修饰的局部变量，只能被赋值一次
        final Long nowTime = System.currentTimeMillis();

        //token过期时间 jwtProfile.getExpirationTime() 单位为s
        final Date expirationDate = new Date(nowTime + jwtProfile.getExpirationTime() * 1000);

        String token = Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date(nowTime))
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, jwtProfile.getApiSecretKey())
                .compact();
        return AccessToken.builder().token(token)
                .expirationTime(expirationDate)
                .loginAccount(subject).build();
    }

    /**
     * 验证token是否还有效 如果返回false，那么验证失败 返回true 验证成功
     * @param token
     * @return
     */
    public boolean validateToken(String token) {
        Claims claims = getClaimsFromToken(token);

        //从token中获取到的用户名
        String usernameFromToken = claims.getSubject();

        //如果返回false token中的时间，早于当前时间，已经失效了
        return isTokenExpired(claims);
    }

    /**
     * 判断token是否失效 也就是比对token中的时间和当前时间
     * @param claims
     * @return
     */
    private boolean isTokenExpired(Claims claims) {
        // 如果token中的失效时间 早于当前时间 则证明token已经失效
         return claims.getExpiration().before(new Date());
    }

    /**
     * 从token中拿到负载信息
     * @param token
     * @return
     */
    public Claims getClaimsFromToken(String token) {
        Claims claims = null;

        claims = Jwts.parser()
                .setSigningKey(jwtProfile.getApiSecretKey())
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }

    /**
     * 从token中获取用户名
     * @param token
     * @return
     */
    public String getSubjectFromToken(String token) {
        Claims claims = getClaimsFromToken(token);

        return claims.getSubject();
    }

    /**
     * 刷新token 返回一个新的token 新的token会在当前时间的基础上，再加上指定的过期时间
     * @param oldToken 旧的token信息
     * @return
     */
    public AccessToken refreshToken(String oldToken) {
        //token反解析
        Claims claims = getClaimsFromToken(oldToken);

        //如果token在指定时间内刷新过，则直接返回原token
        if (tokenRefreshJustBefore(claims)) {
            return AccessToken.builder()
                    .token(oldToken)
                    .loginAccount(claims.getSubject())
                    .expirationTime(claims.getExpiration()).build();
        }

        //没有刷新过，则直接返回新的token
        return createToken(claims.getSubject());
    }

    /**
     * 判断token是否被刷新过
     * @param claims
     * @return
     */
    private boolean tokenRefreshJustBefore(Claims claims) {
        Date refreshDate = new Date();
        //刷新时间在创建时间的指定时间内
        if (refreshDate.after(claims.getExpiration()) && refreshDate.before(DateUtil.offsetSecond(claims.getExpiration(), 1800))) {
            return true;
        }
        return false;
    }
}
