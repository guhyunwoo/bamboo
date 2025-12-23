package bsm.insert.bamboo.domain.oauth.application.dto.request;

public record BsmTokenRequest(
    String clientId,
    String clientSecret,
    String authCode
) {
}
