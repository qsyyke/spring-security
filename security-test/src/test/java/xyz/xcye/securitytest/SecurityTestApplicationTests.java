package xyz.xcye.securitytest;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;

@SpringBootTest
class SecurityTestApplicationTests {

    @Test
    void test1() {
        boolean matches = new BCryptPasswordEncoder().matches(new StringBuffer("123sadf456"), "$2a$10$HfqvfV788kTR/cytntIPFOO2XE1h3uLIhreeSNIwM4m/BBoVVZ9M6");
        System.out.println(matches);
    }

    @Test
    void contextLoads() {

        Date now = new Date(10000);

        String secret = "aurora_secret";
        String username = "aurora";
        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 100000))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();

        System.out.println(token);
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();

        String subject = claims.getSubject();
        Date expiration = claims.getExpiration();
        String audience = claims.getAudience();
        String id = claims.getId();
        Date issuedAt = claims.getIssuedAt();
        String issuer = claims.getIssuer();

        System.out.println();

    }

}
