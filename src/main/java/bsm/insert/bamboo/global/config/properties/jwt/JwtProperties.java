package bsm.insert.bamboo.global.config.properties.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("jwt")
public record JwtProperties(
    String secretKey,
    Long accessTime,
    String header,
    String prefix
) {
}
