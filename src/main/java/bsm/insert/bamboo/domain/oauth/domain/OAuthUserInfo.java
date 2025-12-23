package bsm.insert.bamboo.domain.oauth.domain;

import bsm.insert.bamboo.domain.user.domain.type.Role;

public interface OAuthUserInfo {
    String getProviderId();
    String getName();
    String getEmail();
    String getStudentNumber();
    Integer getGrade();
    Integer getClassNumber();
    Role getRole();
}
