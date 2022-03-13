package xyz.xcye.securitydemo1;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class SecurityDemo1ApplicationTests {

    @Test
    void contextLoads() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String aurora = bCryptPasswordEncoder.encode("aurora");
        System.out.println("加密后的数据: " + aurora);

        boolean matches = bCryptPasswordEncoder.matches(new StringBuffer(""),aurora);
        System.out.println("解密后的数据: " + matches);
    }

}
