package xyz.xcye.securityjwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.Base64Codec;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

@SpringBootTest
class SecurityJwtApplicationTests {

    @Test
    void contextLoads() {
        //System.out.println(System.currentTimeMillis());
        /*String token = Jwts.builder()
                .setId("aurora_docs")
                .setSubject("qsyyke")
                .setIssuer("by aurora")
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512, "secret").compact();

        System.out.println(token);
        String[] split = token.split("\\.");
        //头部
        System.out.println(Base64Codec.BASE64.decodeToString(split[0]));
        //payload
        System.out.println(Base64Codec.BASE64.decodeToString(split[1]));
        //
        System.out.println(Base64Codec.BASE64.decodeToString(split[2]));*/

        Date date = new Date();
        //设置一分钟失效
        long l = date.getTime() + (60 * 1000);

        String token = Jwts.builder()
                .setId("aurora_docs")
                .setSubject("qsyyke")
                .setIssuer("by aurora")
                .setIssuedAt(new Date())
                .setExpiration(new Date(l))
                .claim("logo","aurora.jpg")
                .claim("site","https://aurora.xcye.xyz")
                .signWith(SignatureAlgorithm.HS512, "secret").compact();

        System.out.println(token);
        String[] split = token.split("\\.");

        //解析
        Claims claims = Jwts.parser()
                .setSigningKey("secret")
                .parseClaimsJws(token)
                .getBody();


        System.out.println((String)claims.get("logo"));
        System.out.println((String)claims.get("site"));
    }

}
