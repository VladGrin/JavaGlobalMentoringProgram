package com.epam.spring.service.impl;

import com.epam.spring.domain.UserAccountEntity;
import com.epam.spring.domain.UserProfileEntity;
import com.epam.spring.dto.form.user.UpdateUserProfileForm;
import com.epam.spring.repository.UserProfileRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserProfileServiceImplTest {

    private UserProfileServiceImpl userProfileService;
    @Mock
    private UserProfileRepository userProfileRepository;
    private UserProfileEntity userProfileEntity;
    private String email = "test@test.com";

    @Before
    public void init() {
        userProfileService = new UserProfileServiceImpl(userProfileRepository);
        userProfileEntity = new UserProfileEntity();
        userProfileEntity.setUserAccountEntity(UserAccountEntity.builder().email(email).build());
    }

    @Test
    public void getByUserEmail() {
        userProfileService.getByUserEmail(email);
        verify(userProfileRepository).findByUserEmail(email);
    }

    @Test
    public void whenUserEditProfileFormThenUpdateUserProfile() {

        UserProfileEntity userProfile = UserProfileEntity.builder()
                .firstName("Tom")
                .lastName("Black")
                .userAccountEntity(UserAccountEntity.builder().email(email).build())
                .build();

        //then user wants to update his profile
        UpdateUserProfileForm updateUserProfileForm = UpdateUserProfileForm.builder()
                .firstName("Tom2")
                .lastName("Black")
                .build();

        when(userProfileRepository.findByUserEmail(email)).thenReturn(userProfile);
        when(userProfileRepository.save(any())).thenAnswer(i -> i.getArgument(0));

        UserProfileEntity updatedUserProfile = userProfileService.updateProfile(email, updateUserProfileForm);
        assertEquals(userProfile, updatedUserProfile);
    }

    @Test
    public void saveUserProfileEntity() {

        UserProfileEntity userProfileUpdated = UserProfileEntity.builder()
                .firstName("Tomas")
                .lastName("Jack")
                .userAccountEntity(UserAccountEntity.builder().email(email).build())
                .build();

        when(userProfileRepository.save(any())).thenReturn(userProfileUpdated);

        UserProfileEntity updatedUserProfile = userProfileService.save(userProfileUpdated);

        assertEquals(userProfileUpdated, updatedUserProfile);

        verify(userProfileRepository).save(userProfileUpdated);
    }

    @Test
    public void updateUserProfileIfCompanyNameIsNull() {
        UserProfileEntity userProfile = UserProfileEntity.builder()
                .firstName("Tom")
                .lastName("Black")
                .userAccountEntity(UserAccountEntity.builder().email(email).build())
                .build();

        UpdateUserProfileForm updateUserProfileFormCompanyNameIsNull = UpdateUserProfileForm.builder()
                .firstName("Tommmm")
                .lastName("Black")
                .build();

        when(userProfileRepository.findByUserEmail(email)).thenReturn(userProfile);
        when(userProfileRepository.save(any())).thenAnswer(i -> i.getArgument(0));

        UserProfileEntity updatedUserProfileCompanyNameIsNull = userProfileService.updateProfile(email, updateUserProfileFormCompanyNameIsNull);
        assertEquals(userProfile, updatedUserProfileCompanyNameIsNull);

        verify(userProfileRepository).save(updatedUserProfileCompanyNameIsNull);
    }

    @Test
    public void updateUserProfileIfCompanyNameIsEmpty() {
        UserProfileEntity userProfile = UserProfileEntity.builder()
                .firstName("Tom")
                .lastName("Black")
                .userAccountEntity(UserAccountEntity.builder().email(email).build())
                .build();

        UpdateUserProfileForm updateUserProfileFormCompanyNameIsEmpty = UpdateUserProfileForm.builder()
                .firstName("Tommmm")
                .lastName("Black")
                .companyName("")
                .build();

        when(userProfileRepository.findByUserEmail(email)).thenReturn(userProfile);
        when(userProfileRepository.save(any())).thenAnswer(i -> i.getArgument(0));

        UserProfileEntity updatedUserProfileCompanyNameIsEmpty = userProfileService.updateProfile(email, updateUserProfileFormCompanyNameIsEmpty);
        assertEquals(userProfile, updatedUserProfileCompanyNameIsEmpty);

        verify(userProfileRepository).save(updatedUserProfileCompanyNameIsEmpty);
    }

    @Test
    public void updateUserProfileIfCompanyNameIsFilled() {
        UserProfileEntity userProfile = UserProfileEntity.builder()
                .firstName("Tom")
                .lastName("Black")
                .userAccountEntity(UserAccountEntity.builder().email(email).build())
                .build();

        UpdateUserProfileForm updateUserProfileFormWhenCompanyNameIsChanged = UpdateUserProfileForm.builder()
                .firstName("Tommmm")
                .lastName("Black")
                .build();

        when(userProfileRepository.findByUserEmail(email)).thenReturn(userProfile);
        when(userProfileRepository.save(any())).thenAnswer(i -> i.getArgument(0));
        UserProfileEntity updatedUserProfileCompanyNameIsChanged = userProfileService.updateProfile(email, updateUserProfileFormWhenCompanyNameIsChanged);
        assertEquals(userProfile, updatedUserProfileCompanyNameIsChanged);
        verify(userProfileRepository).save(updatedUserProfileCompanyNameIsChanged);
    }

    @Test
    public void updateUserProfileIfOccupationNameIsNull() {
        UserProfileEntity userProfile = UserProfileEntity.builder()
                .firstName("Tom")
                .lastName("Black")
                .userAccountEntity(UserAccountEntity.builder().email(email).build())
                .build();

        UpdateUserProfileForm updateUserProfileFormOccupationNameIsNull = UpdateUserProfileForm.builder()
                .firstName("Tommmm")
                .lastName("Black")
                .occupationName(null)
                .build();

        when(userProfileRepository.findByUserEmail(email)).thenReturn(userProfile);
        when(userProfileRepository.save(any())).thenAnswer(i -> i.getArgument(0));
        UserProfileEntity updatedUserProfileOccupationNameIsNull = userProfileService.updateProfile(email, updateUserProfileFormOccupationNameIsNull);
        assertEquals(userProfile, updatedUserProfileOccupationNameIsNull);


        verify(userProfileRepository).save(updatedUserProfileOccupationNameIsNull);
    }

    @Test
    public void updateUserProfileIfOccupationNameIsEmpty() {
        UserProfileEntity userProfile = UserProfileEntity.builder()
                .firstName("Tom")
                .lastName("Black")
                .userAccountEntity(UserAccountEntity.builder().email(email).build())
                .build();

        UpdateUserProfileForm updateUserProfileFormOccupationNameIsEmpty = UpdateUserProfileForm.builder()
                .firstName("Tommmm")
                .lastName("Black")
                .build();

        when(userProfileRepository.findByUserEmail(email)).thenReturn(userProfile);
        when(userProfileRepository.save(any())).thenAnswer(i -> i.getArgument(0));
        UserProfileEntity updatedUserProfileOccupationNameIsEmpty = userProfileService.updateProfile(email, updateUserProfileFormOccupationNameIsEmpty);
        assertEquals(userProfile, updatedUserProfileOccupationNameIsEmpty);

        verify(userProfileRepository).save(updatedUserProfileOccupationNameIsEmpty);
    }

    @Test
    public void updateUserProfileIfOccupationNameIsFilled() {
        UserProfileEntity userProfile = UserProfileEntity.builder()
                .firstName("Tom")
                .lastName("Black")
                .userAccountEntity(UserAccountEntity.builder().email(email).build())
                .build();

        UpdateUserProfileForm updateUserProfileFormWhenOccupationNameIsChanged = UpdateUserProfileForm.builder()
                .firstName("Tommmm")
                .lastName("Black")
                .build();

        when(userProfileRepository.findByUserEmail(email)).thenReturn(userProfile);
        when(userProfileRepository.save(any())).thenAnswer(i -> i.getArgument(0));
        UserProfileEntity updatedUserProfileOccupationNameIsChanged = userProfileService.updateProfile(email, updateUserProfileFormWhenOccupationNameIsChanged);
        assertEquals(userProfile, updatedUserProfileOccupationNameIsChanged);

        verify(userProfileRepository).save(updatedUserProfileOccupationNameIsChanged);
    }
}
