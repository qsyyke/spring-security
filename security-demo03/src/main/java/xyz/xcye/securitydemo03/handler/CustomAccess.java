package xyz.xcye.securitydemo03.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * @author ：qsyyke
 * @description：TODO
 * @date ：2022/3/12 22:10
 */

@Component
public class CustomAccess {
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        String requestURI = request.getRequestURI();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            String authority = grantedAuthority.getAuthority();
            if (authority.equals(requestURI)) {
                return true;
            }
        }
        return false;
    }

    /*public boolean hasPermission(String url) {
        if (url.equals("/main")) {
            return true;
        }
        return false;
    }*/
}
