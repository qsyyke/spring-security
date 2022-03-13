package xyz.xcye.securitydemo03.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：qsyyke
 * @description：TODO
 * @date ：2022/3/12 19:22
 */

@Slf4j
@RestController
public class LoginController {

    @PostMapping("/loginSuccess")
    public String loginSuccess() {
        return "loginSuccess";
    }

    @PostMapping("/login")
    public String login(String username,String password) {
        log.info("执行了");
        return "aurora1";
    }

    @PostMapping("/login2")
    public String login2(String username,String password) {
        log.info("执行了");
        return "aurora2";
    }
}
