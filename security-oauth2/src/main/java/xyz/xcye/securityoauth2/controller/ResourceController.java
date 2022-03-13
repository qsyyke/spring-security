package xyz.xcye.securityoauth2.controller;

import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：qsyyke
 * @description：TODO
 * @date ：2022/3/13 12:06
 */


@RestController
@RequestMapping("/resource")
public class ResourceController {

    @GetMapping("/userInfo")
    public String userInfo(Authentication authentication) {
        return authentication.toString();
    }

    @GetMapping("/info")
    public String info() {
        return "info";
    }


}
