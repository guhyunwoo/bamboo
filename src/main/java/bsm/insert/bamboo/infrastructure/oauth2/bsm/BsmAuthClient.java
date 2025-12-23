package bsm.insert.bamboo.infrastructure.oauth2.bsm;

import bsm.insert.bamboo.domain.oauth.application.dto.request.BsmResourceRequest;
import bsm.insert.bamboo.domain.oauth.application.dto.response.BsmResourceResponse;
import bsm.insert.bamboo.domain.oauth.application.dto.request.BsmTokenRequest;
import bsm.insert.bamboo.domain.oauth.application.dto.response.BsmTokenResponse;
import bsm.insert.bamboo.global.config.properties.oauth.bsm.BsmOAuthProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@RequiredArgsConstructor
public class BsmAuthClient {

    private final RestClient restClient;
    private final BsmOAuthProperties properties;

    public String exchangeToken(String authorizationCode) {
        BsmTokenRequest request = new BsmTokenRequest(
                properties.clientId(),
                properties.clientSecret(),
                authorizationCode
        );

        BsmTokenResponse response = restClient.post()
                .uri(properties.tokenUri())
                .body(request)
                .retrieve()
                .body(BsmTokenResponse.class);

        if (response == null) {
            throw new RuntimeException("Failed to exchange token");
        }

        return response.token();
    }

    public BsmResourceResponse fetchUserResource(String accessToken) {
        BsmResourceRequest request = new BsmResourceRequest(
                properties.clientId(),
                properties.clientSecret(),
                accessToken
        );

        BsmResourceResponse response = restClient.post()
                .uri(properties.resourceUri())
                .body(request)
                .retrieve()
                .body(BsmResourceResponse.class);

        if (response == null) {
            throw new RuntimeException("Failed to fetch user resource");
        }

        return response;
    }
}
