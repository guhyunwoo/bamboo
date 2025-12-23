package bsm.insert.bamboo.domain.user.domain;

import bsm.insert.bamboo.domain.user.domain.type.Role;
import bsm.insert.bamboo.global.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_user")
@Getter
@NoArgsConstructor
public class User extends BaseEntity {
    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    private String password;

    private String studentNumber;

    private Integer grade;

    private Integer classNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(String name, String email, String studentNumber, Integer grade, Integer classNumber, Role role) {
        this.name = name;
        this.email = email;
        this.password = null;
        this.studentNumber = studentNumber;
        this.grade = grade;
        this.classNumber = classNumber;
        this.role = role;
    }
}
