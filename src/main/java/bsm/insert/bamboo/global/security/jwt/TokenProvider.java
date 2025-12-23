package bsm.insert.bamboo.global.security.jwt;

import bsm.insert.bamboo.domain.user.domain.type.Role;
import bsm.insert.bamboo.global.config.properties.jwt.JwtProperties;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class TokenProvider {

    private final JwtProperties jwtProperties;
    private final SecretKey secretKey;

    private static final String ROLE_PREFIX = "ROLE_";

    public String generateTokenHandle(Long userId, Role role) {
        return generateToken(userId, role);
    }

    private String generateToken(Long userId, Role role) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtProperties.accessTime());

        return Jwts.builder()
                .header()
                    .type("jwt")
                    .and()
                .subject(String.valueOf(userId))
                .claim("role", ROLE_PREFIX + role.name())
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(secretKey)
                .compact();
    }
}
