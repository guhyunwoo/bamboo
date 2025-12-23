package bsm.insert.bamboo.global.config.properties.oauth.bsm;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("bsm.oauth")
public record BsmOAuthProperties(
    String clientId,
    String clientSecret,
    String redirectUri,
    String oauthUri,
    String tokenUri,
    String resourceUri,
    String frontEndRedirectUri
) {
}
