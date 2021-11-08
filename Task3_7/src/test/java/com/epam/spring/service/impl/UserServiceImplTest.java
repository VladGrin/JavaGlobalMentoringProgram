package com.epam.spring.service.impl;

import com.epam.spring.domain.RoleEntity;
import com.epam.spring.domain.UserAccountEntity;
import com.epam.spring.dto.form.user.SignUpFormData;
import com.epam.spring.repository.RoleRepository;
import com.epam.spring.repository.UserAccountRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    private UserServiceImpl userService;
    @Mock
    private UserAccountRepository userAccountRepository;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    private UserAccountEntity userAccount;
    private String email = "test@test.com";

    @Before
    public void init() {
        userService = new UserServiceImpl(userAccountRepository, roleRepository, passwordEncoder);
        userAccount = new UserAccountEntity();
        userAccount.setEmail(email);
    }

    @Test
    public void findByEmail() {
        userService.findByEmail(email);

        verify(userAccountRepository).findByEmail(email);
    }

    @Test
    public void whenFindByEmailThenReturnUserAccountEntity() {
        when(userAccountRepository.findByEmail(email)).thenReturn(userAccount);

        UserAccountEntity foundUserAccount = userService.findByEmail(email);

        assertEquals(userAccount, foundUserAccount);
    }

    @Test
    public void whenUserFillSignUpFormThenSaveUser() {
        SignUpFormData signUpFormData = new SignUpFormData("First Name", "Last Name", email, "111111");

        String encodedPass = "encodedPassword";
        when(passwordEncoder.encode(signUpFormData.getPassword())).thenReturn(encodedPass);

        RoleEntity role = new RoleEntity();
        role.setName("ROLE_ATTENDEE");
        when(roleRepository.findRoleByName("ROLE_ATTENDEE")).thenReturn(role);

        userService.signUp(signUpFormData);

        ArgumentCaptor<UserAccountEntity> userAccountEntityCaptor = ArgumentCaptor.forClass(UserAccountEntity.class);

        verify(passwordEncoder).encode(signUpFormData.getPassword());
        verify(userAccountRepository).save(userAccountEntityCaptor.capture());

        UserAccountEntity userAccountEntityCaptorValue = userAccountEntityCaptor.getValue();
        assertEquals(signUpFormData.getEmail(), userAccountEntityCaptorValue.getEmail());
        assertEquals(encodedPass, userAccountEntityCaptorValue.getPassword());

        assertEquals(signUpFormData.getFirstName(), userAccountEntityCaptorValue.getUserProfileEntity().getFirstName());
        assertEquals(signUpFormData.getLastName(), userAccountEntityCaptorValue.getUserProfileEntity().getLastName());
        assertTrue(userAccountEntityCaptorValue.getRoles().contains(role));
    }
}