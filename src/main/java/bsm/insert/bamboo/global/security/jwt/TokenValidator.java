package bsm.insert.bamboo.global.security.jwt;

import bsm.insert.bamboo.global.security.auth.TokenType;
import bsm.insert.bamboo.global.security.exception.InvalidTokenException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class TokenValidator {

    private static final String BEARER_PREFIX = "Bearer ";

    public void validateAuthorizationHeader(String authorizationHeader) {
        if (!StringUtils.hasText(authorizationHeader)) {
            throw InvalidTokenException.EXCEPTION;
        }

        if (!authorizationHeader.startsWith(BEARER_PREFIX)) {
            throw InvalidTokenException.EXCEPTION;
        }
    }

    public void validateTokenType(TokenType tokenType, TokenType expectedType) {
        if (tokenType != expectedType) {
            throw InvalidTokenException.EXCEPTION;
        }
    }
}
