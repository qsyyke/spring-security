package xyz.xcye.securitydemo03.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.RememberMeServices;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ：qsyyke
 * @description：TODO
 * @date ：2022/3/13 09:08
 */


public class CustomRememberMeServices implements RememberMeServices {

    @Override
    public Authentication autoLogin(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("自动登录");
        return null;
    }

    @Override
    public void loginFail(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("登录失败");
    }

    @Override
    public void loginSuccess(HttpServletRequest request, HttpServletResponse response, Authentication successfulAuthentication) {
        System.out.println("登录成功");

    }
}
