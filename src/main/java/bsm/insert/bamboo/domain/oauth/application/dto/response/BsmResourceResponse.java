package bsm.insert.bamboo.domain.oauth.application.dto.response;

public record BsmResourceResponse(
    String name,
    String email,
    String studentNo,
    Integer grade,
    Integer classNo,
    String role
) {
}
