package xyz.xcye.securitydemo1.entity;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ：qsyyke
 * @description：TODO
 * @date ：2022/3/8 23:29
 */
@Slf4j
@Data
public class Users {
    private Integer id;
    private String username;
    private String password;
    private String role;
}
