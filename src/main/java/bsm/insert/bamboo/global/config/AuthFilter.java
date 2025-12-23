package bsm.insert.bamboo.global.config;

import bsm.insert.bamboo.global.config.properties.jwt.JwtProperties;
import bsm.insert.bamboo.global.security.jwt.TokenResolver;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AuthFilter extends OncePerRequestFilter {

    private final JwtProperties jwtProperties;
    private final TokenResolver tokenResolver;

    private static final List<String> EXCLUDE_PATHS =
            List.of(
                    "/api/v3/auth/**",
                    "/api/v3/item/**"
            );

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        return EXCLUDE_PATHS.stream().anyMatch(path::startsWith);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String headerValue = request.getHeader(jwtProperties.header());

        if (headerValue != null) {
            String token = tokenResolver.stripPrefixScheme(headerValue);
            Authentication authentication = tokenResolver.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }
}
