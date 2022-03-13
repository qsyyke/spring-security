package xyz.xcye.securitydemo03.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ：qsyyke
 * @description：TODO
 * @date ：2022/3/12 19:56
 */


public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //response.sendRedirect("https://aurora.xcye.xyz");
        Object principal = authentication.getPrincipal();

        WebAuthenticationDetails webAuthenticationDetails = (WebAuthenticationDetails) authentication.getDetails();
        String remoteAddress = webAuthenticationDetails.getRemoteAddress();
        String sessionId = webAuthenticationDetails.getSessionId();
        System.out.println("ip: " + remoteAddress);

    }
}
