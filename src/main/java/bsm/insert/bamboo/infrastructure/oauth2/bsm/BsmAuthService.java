package bsm.insert.bamboo.infrastructure.oauth2.bsm;

import bsm.insert.bamboo.domain.oauth.application.dto.response.BsmResourceResponse;
import bsm.insert.bamboo.infrastructure.oauth2.OAuth2AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BsmAuthService implements OAuth2AuthService {

    private final BsmAuthClient bsmAuthClient;

    @Override
    public Map<String, Object> getUserInfo(String authorizationCode) {
        String accessToken = bsmAuthClient.exchangeToken(authorizationCode);
        BsmResourceResponse response = bsmAuthClient.fetchUserResource(accessToken);
        return convertToMap(response);
    }

    private Map<String, Object> convertToMap(BsmResourceResponse response) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", response.name());
        map.put("email", response.email());
        map.put("studentNo", response.studentNo());
        map.put("grade", response.grade());
        map.put("classNo", response.classNo());
        map.put("role", response.role());
        return map;
    }
}
