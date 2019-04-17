package org.bytewen.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Date;
@ConfigurationProperties("jwt.config")
public class JwtUtils {

    private String key;
    private long ttl;

    public void setKey(String key) {
        this.key = key;
    }

    public void setTtl(long ttl) {
        this.ttl = ttl;
    }

    public String createJwt(String id,String subject,Object others){
        long now = System.currentTimeMillis();
        JwtBuilder builder = Jwts.builder().setId(id)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .claim("others", others);
        if(ttl>0){
            builder.setExpiration(new Date(now+ttl));
        }
        return builder.compact();
    }

    public Claims parseJwt(String jwtStr){
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJwt(jwtStr)
                .getBody();
    }
}
