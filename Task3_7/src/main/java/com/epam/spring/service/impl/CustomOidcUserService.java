package com.epam.spring.service.impl;

import com.epam.spring.domain.RoleEntity;
import com.epam.spring.domain.UserAccountEntity;
import com.epam.spring.domain.UserProfileEntity;
import com.epam.spring.domain.oauth2.OAuth2UserInfo;
import com.epam.spring.domain.oauth2.OAuth2UserInfoFactory;
import com.epam.spring.exception.OAuth2AuthenticationProcessingException;
import com.epam.spring.repository.RoleRepository;
import com.epam.spring.repository.UserAccountRepository;
import com.epam.spring.repository.UserProfileRepository;
import com.epam.spring.security.JwtUserAccount;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CustomOidcUserService extends OidcUserService {

    private final UserAccountRepository userAccountRepository;
    private final UserProfileRepository userProfileRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Autowired
    public CustomOidcUserService(UserAccountRepository userAccountRepository,
                                 UserProfileRepository userProfileRepository,
                                 PasswordEncoder passwordEncoder,
                                 RoleRepository roleRepository) {
        this.userAccountRepository = userAccountRepository;
        this.userProfileRepository = userProfileRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public OidcUser loadUser(OidcUserRequest oidcUserRequest) throws AuthenticationException {
        OidcUser oidcUser = super.loadUser(oidcUserRequest);

        try {
            return processOAuth2User(oidcUserRequest, oidcUser);
        } catch (AuthenticationException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    private OidcUser processOAuth2User(OidcUserRequest oidcUserRequest, OidcUser oidcUser) {
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(
                oidcUserRequest.getClientRegistration().getRegistrationId(), oidcUser.getAttributes());
        if (StringUtils.isEmpty(oAuth2UserInfo.getEmail())) {
            throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
        }

        UserAccountEntity userAccountOptional = userAccountRepository.findByEmail(oAuth2UserInfo.getEmail());
        UserAccountEntity user;
        if (userAccountOptional != null) {
            user = updateExistingUser(userAccountOptional, oAuth2UserInfo);
        } else {
            user = registerNewUser(oAuth2UserInfo);
        }

        return JwtUserAccount.create(user, oidcUser.getAttributes());
    }


    private UserAccountEntity registerNewUser(OAuth2UserInfo oAuth2UserInfo) {

        String encodedPassword = passwordEncoder.encode(oAuth2UserInfo.getId());

        RoleEntity role = roleRepository.findRoleByName("ROLE_ATTENDEE");
        List<RoleEntity> userRoles = new ArrayList<>();
        userRoles.add(role);

        UserProfileEntity userProfileEntity = UserProfileEntity.builder()
                .firstName(oAuth2UserInfo.getFirstName())
                .lastName(oAuth2UserInfo.getLastName())
                .build();

        UserAccountEntity userAccountEntity = UserAccountEntity.builder()
                .email(oAuth2UserInfo.getEmail())
                .password(encodedPassword)
                .roles(userRoles)
                .userProfileEntity(userProfileEntity)
                .build();

        userProfileEntity.setUserAccountEntity(userAccountEntity);

        return userAccountRepository.save(userAccountEntity);
    }

    private UserAccountEntity updateExistingUser(UserAccountEntity existingUser, OAuth2UserInfo oAuth2UserInfo) {
        UserProfileEntity userProfileEntity = existingUser.getUserProfileEntity();
        userProfileEntity.setFirstName(oAuth2UserInfo.getFirstName());
        userProfileEntity.setLastName(oAuth2UserInfo.getLastName());
        userProfileRepository.save(userProfileEntity);
        return existingUser;
    }
}