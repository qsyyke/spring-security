package xyz.xcye.securitytest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：qsyyke
 * @description：TODO
 * @date ：2022/3/12 11:12
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int id;
    private String username;
    private String password;
    private String role;
}
