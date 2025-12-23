package bsm.insert.bamboo.global.security.jwt;

import bsm.insert.bamboo.global.config.properties.jwt.JwtProperties;
import bsm.insert.bamboo.global.security.exception.ExpiredTokenException;
import bsm.insert.bamboo.global.security.exception.InvalidTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TokenResolver {

    private final SecretKey secretKey;
    private final JwtProperties jwtProperties;
    private final TokenValidator tokenValidator;

    public String stripPrefixScheme(String authorizationHeader) {
        tokenValidator.validateAuthorizationHeader(authorizationHeader);
        return authorizationHeader.substring(jwtProperties.prefix().length()).trim();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = getTokenBody(token);

        String userId = claims.getSubject();
        String role = claims.get("role", String.class);

        return new UsernamePasswordAuthenticationToken(
                userId,
                null,
                List.of(new SimpleGrantedAuthority(role))
        );
    }

    private Claims getTokenBody(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (ExpiredJwtException e) {
            throw ExpiredTokenException.EXCEPTION;
        } catch (Exception e) {
            throw InvalidTokenException.EXCEPTION;
        }
    }
}
