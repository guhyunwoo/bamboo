package bsm.insert.bamboo.domain.oauth.application.service;

import bsm.insert.bamboo.global.config.properties.oauth.bsm.BsmOAuthProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BsmOAuthService {

    private final BsmOAuthProperties bsmOAuthProperties;
    private final OAuth2AuthFacade oAuth2AuthFacade;

    public String getOAuthRedirectUrl() {
        return bsmOAuthProperties.oauthUri() + "?" +
                "clientId=" + bsmOAuthProperties.clientId() + "&" +
                "redirectURI=" + bsmOAuthProperties.redirectUri();
    }

    public String handleCallback(String authCode) {
        return oAuth2AuthFacade.bsmLogin(authCode);
    }

    public String getFrontEndRedirectUrl(String token) {
        return bsmOAuthProperties.frontEndRedirectUri() + "?token=" + token;
    }
}
