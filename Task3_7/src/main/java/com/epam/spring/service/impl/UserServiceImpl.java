package com.epam.spring.service.impl;

import com.epam.spring.domain.RoleEntity;
import com.epam.spring.domain.UserAccountEntity;
import com.epam.spring.domain.UserProfileEntity;
import com.epam.spring.dto.form.user.SignUpFormData;
import com.epam.spring.repository.RoleRepository;
import com.epam.spring.repository.UserAccountRepository;
import com.epam.spring.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private UserAccountRepository userAccountRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;


    @Override
    public UserAccountEntity findByEmail(String email) {
        return userAccountRepository.findByEmail(email);
    }

    @Override
    public UserAccountEntity signUp(SignUpFormData formData) {
        String encodedPassword = passwordEncoder.encode(formData.getPassword());
        RoleEntity role = roleRepository.findRoleByName("ROLE_ATTENDEE");
        List<RoleEntity> userRoles = new ArrayList<>();
        userRoles.add(role);


        UserProfileEntity userProfileEntity = UserProfileEntity.builder()
                .firstName(formData.getFirstName())
                .lastName(formData.getLastName())
                .build();


        UserAccountEntity userAccountEntity = UserAccountEntity.builder()
                .email(formData.getEmail())
                .password(encodedPassword)
                .roles(userRoles)
                .userProfileEntity(userProfileEntity)
                .build();


        userProfileEntity.setUserAccountEntity(userAccountEntity);
        log.info("signUp method register new UserAccount with UserProfile");
        return userAccountRepository.save(userAccountEntity);
    }
}
