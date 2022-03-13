package xyz.xcye.securityoauth2.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：qsyyke
 * @description：TODO
 * @date ：2022/3/12 19:22
 */

@Slf4j
@RestController
@RequestMapping("/login")
public class LoginController {

    @PostMapping("/success")
    public String successUrl(Authentication authentication) {
        return authentication.toString();
    }

    @GetMapping("/getAll")
    public String getAll() {
        return "getAll";
    }


}
