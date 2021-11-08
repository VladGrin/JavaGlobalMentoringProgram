package com.epam.spring.service;

import com.epam.spring.domain.UserAccountEntity;
import com.epam.spring.dto.form.user.SignUpFormData;

public interface UserService {

    UserAccountEntity findByEmail(String email);

    UserAccountEntity signUp(SignUpFormData formData);
}
