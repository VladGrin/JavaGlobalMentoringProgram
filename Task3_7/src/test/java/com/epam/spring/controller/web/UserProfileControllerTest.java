package com.epam.spring.controller.web;

import com.epam.spring.domain.UserAccountEntity;
import com.epam.spring.domain.UserProfileEntity;
import com.epam.spring.dto.form.user.UpdateUserProfileForm;
import com.epam.spring.service.UserService;
import com.epam.spring.service.impl.UserProfileServiceImpl;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserProfileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserProfileServiceImpl userProfileService;

    @MockBean
    private UserService userService;

    private static final ObjectMapper om = new ObjectMapper();
    private String email;
    private UpdateUserProfileForm updateUserProfileForm;
    private UserAccountEntity userAccountEntity;

    @Before
    public void init() {
        email = "email@gmail.com";
        userAccountEntity = new UserAccountEntity();
        UserProfileEntity userProfileEntity = UserProfileEntity.builder()
                .firstName("Name")
                .lastName("Last")
                .userAccountEntity(UserAccountEntity.builder().email(email).build())
                .build();
        userAccountEntity.setUserProfileEntity(userProfileEntity);

        updateUserProfileForm = new UpdateUserProfileForm();
        updateUserProfileForm.setFirstName("newName");
        updateUserProfileForm.setLastName("newLast");

        when(userProfileService.updateProfile(any(), any())).thenReturn(userProfileEntity);
    }

    @Test
    public void shouldReturnUserByEmail() throws Exception {
        when(userService.findByEmail(any())).thenReturn(userAccountEntity);

        mockMvc.perform(get("/api/v1/profile/" + email))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Name"))
                .andExpect(jsonPath("$.lastName").value("Last"));

        verify(userService, times(1)).findByEmail(email);
    }

    @Test
    public void shouldUpdateUserByEmail() throws Exception {
        mockMvc.perform(put("/api/v1/profile/" + email)
                .content(om.writeValueAsString(updateUserProfileForm))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(userProfileService, times(1)).updateProfile(email, updateUserProfileForm);
    }
}
