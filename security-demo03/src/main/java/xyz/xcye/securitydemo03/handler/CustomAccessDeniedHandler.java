package xyz.xcye.securitydemo03.handler;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author ：qsyyke
 * @description：TODO
 * @date ：2022/3/12 21:33
 */


public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(403);
        PrintWriter writer = response.getWriter();
        writer.write("{\"code\": \"403\",\"msg\": \"权限不足\"}");
        writer.flush();
        writer.close();

    }
}
