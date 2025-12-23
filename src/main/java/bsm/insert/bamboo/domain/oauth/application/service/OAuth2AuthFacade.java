package bsm.insert.bamboo.domain.oauth.application.service;

import bsm.insert.bamboo.domain.oauth.application.mapper.OAuth2Mapper;
import bsm.insert.bamboo.domain.oauth.domain.BsmOAuth2UserInfo;
import bsm.insert.bamboo.domain.oauth.domain.OAuthUserInfo;
import bsm.insert.bamboo.domain.user.repository.UserRepository;
import bsm.insert.bamboo.domain.user.domain.User;
import bsm.insert.bamboo.global.security.jwt.TokenProvider;
import bsm.insert.bamboo.infrastructure.oauth2.bsm.BsmAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class OAuth2AuthFacade {

    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;
    private final BsmAuthService bsmAuthService;

    @Transactional
    public String bsmLogin(String authorizationCode) {
        Map<String, Object> userInfoMap = bsmAuthService.getUserInfo(authorizationCode);
        OAuthUserInfo oAuthUserInfo = new BsmOAuth2UserInfo(userInfoMap);
        return processOAuth2Login(oAuthUserInfo);
    }

    private String processOAuth2Login(OAuthUserInfo oAuthUserInfo) {
        String email = oAuthUserInfo.getEmail();

        User user = userRepository.findByEmail(email)
                .orElseGet(() -> createUser(oAuthUserInfo));

        return tokenProvider.generateTokenHandle(user.getId(), user.getRole());
    }

    private User createUser(OAuthUserInfo oAuthUserInfo) {
        User newUser = OAuth2Mapper.toUserEntity(oAuthUserInfo);
        return userRepository.save(newUser);
    }
}
