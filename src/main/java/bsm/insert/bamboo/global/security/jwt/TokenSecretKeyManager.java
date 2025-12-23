package bsm.insert.bamboo.global.security.jwt;

import bsm.insert.bamboo.global.config.properties.jwt.JwtProperties;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;

@Component
@RequiredArgsConstructor
public class TokenSecretKeyManager {

    private final JwtProperties jwtProperties;

    @Bean
    public SecretKey getDecodedKey() {
        return Keys.hmacShaKeyFor(
                Base64.getDecoder().decode(jwtProperties.secretKey())
        );
    }
}
