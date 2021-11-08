package com.epam.spring.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user_accounts", schema = "management")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Builder
@Data
@ToString(exclude = {"userProfileEntity","roles"})
public class UserAccountEntity extends AbstractEntity {

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;


    @OneToOne(mappedBy = "userAccountEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private UserProfileEntity userProfileEntity;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<RoleEntity> roles;
}
