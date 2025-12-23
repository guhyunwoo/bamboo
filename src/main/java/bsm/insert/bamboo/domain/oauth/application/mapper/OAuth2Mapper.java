package bsm.insert.bamboo.domain.oauth.application.mapper;

import bsm.insert.bamboo.domain.oauth.domain.OAuthUserInfo;
import bsm.insert.bamboo.domain.user.domain.User;

public final class OAuth2Mapper {

    public static User toUserEntity(OAuthUserInfo oAuthUserInfo) {
        return User.builder()
                .name(oAuthUserInfo.getName())
                .email(oAuthUserInfo.getEmail())
                .studentNumber(oAuthUserInfo.getStudentNumber())
                .grade(oAuthUserInfo.getGrade())
                .classNumber(oAuthUserInfo.getClassNumber())
                .role(oAuthUserInfo.getRole())
                .build();
    }
}
