package xyz.xcye.securitytest.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import xyz.xcye.securitytest.profile.JwtProfile;
import xyz.xcye.securitytest.utils.JwtProvider;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ：qsyyke
 * @description：TODO
 * @date ：2022/3/12 13:32
 */

@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private JwtProfile jwtProfile;

    /**
     * 1. 从请求头中获取到携带上来的token
     * 2. 如果token为null，那么返回错误信息，提示需要登录
     * 3. 如果不为空，则从token中获取到过期时间，如果token已经失效，则提示过期，提示重新登录
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        response.setContentType("text/json;charset=utf-8");
        log.info(requestURI);

        if ("/user/login".equals(requestURI)) {
            filterChain.doFilter(request,response);
        }else {
            //不是登录操作
            String msg = "";

            String tokenByRequest = jwtProvider.getTokenByRequest(request);
            if (!StringUtils.hasLength(tokenByRequest)) {
                msg = "你未进行登录操作";
                response.getWriter().write(msg);
                return;
            }

            //获取token信息 判断是否过期
            if (!jwtProvider.validateToken(tokenByRequest)) {
                msg = "token已经过期了";
                response.getWriter().write(msg);
                return;
            }

            //token没有过期
            filterChain.doFilter(request,response);
        }
    }
}
