package bsm.insert.bamboo.infrastructure.oauth2;

import java.util.Map;

public interface OAuth2AuthService {
    Map<String, Object> getUserInfo(String accessToken);
}
