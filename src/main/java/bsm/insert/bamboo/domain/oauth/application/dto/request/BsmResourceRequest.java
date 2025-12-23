package bsm.insert.bamboo.domain.oauth.application.dto.request;

public record BsmResourceRequest(
    String clientId,
    String clientSecret,
    String token
) {
}
