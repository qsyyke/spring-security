package xyz.xcye.securitydemo1.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：qsyyke
 * @description：TODO
 * @date ：2022/3/8 17:51
 */
@Slf4j
@RestController
@RequestMapping("/sec")
public class SecurityController {

    @GetMapping("/demo1")
    public String demo1() {
        log.info("");
        return "hello security demo1";
    }

    @GetMapping("/demo2")
    public String demo2() {
        log.info("");
        return "hello security demo2";
    }

    @GetMapping("/success")
    public String success() {
        log.info("");
        return "hello security success";
    }

    @GetMapping("/hello")
    public String hello() {
        log.info("");
        return "hello security hello";
    }

    @GetMapping("/admin")
    public String admin() {
        log.info("");
        return "hello security admin";
    }

    @Secured({"ROLE_aurora","ROLE_qsyyke"})
    @GetMapping("/update")
    public String update() {
        return "hello security update";
    }


    @PreAuthorize("hasAnyAuthority('admins')")
    @GetMapping("/pre")
    public String pre() {
        System.out.println("进入方法");
        return "hello security pre";
    }

    @PreAuthorize("hasAnyAuthority('post')")
    @GetMapping("/post")
    public String post() {
        System.out.println("先进入方法");
        return "hello security post";
    }
}
