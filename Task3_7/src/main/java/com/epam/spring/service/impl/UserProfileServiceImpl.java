package com.epam.spring.service.impl;

import com.epam.spring.domain.UserProfileEntity;
import com.epam.spring.dto.form.user.UpdateUserProfileForm;
import com.epam.spring.repository.UserProfileRepository;
import com.epam.spring.service.UserProfileService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository userProfileRepository;

    @Override
    public UserProfileEntity updateProfile(String email, UpdateUserProfileForm form) {

        UserProfileEntity profileEntity = userProfileRepository.findByUserEmail(email);

        profileEntity.setFirstName(form.getFirstName());
        profileEntity.setLastName(form.getLastName());

        return userProfileRepository.save(profileEntity);
    }

    @Override
    public UserProfileEntity getByUserEmail(String email) {
        return userProfileRepository.findByUserEmail(email);
    }

    @Override
    public UserProfileEntity save(UserProfileEntity userProfileEntity) {
        return userProfileRepository.save(userProfileEntity);
    }
}


