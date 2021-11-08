package com.epam.spring.service;

import com.epam.spring.domain.UserProfileEntity;
import com.epam.spring.dto.form.user.UpdateUserProfileForm;

public interface UserProfileService {

    UserProfileEntity updateProfile(String email, UpdateUserProfileForm form);

    UserProfileEntity getByUserEmail(String email);

    UserProfileEntity save(UserProfileEntity userProfileEntity);
}
