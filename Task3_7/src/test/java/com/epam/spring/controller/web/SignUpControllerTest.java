package com.epam.spring.controller.web;

import com.epam.spring.domain.UserAccountEntity;
import com.epam.spring.dto.form.user.SignUpFormData;
import com.epam.spring.service.UserService;
import org.codehaus.jackson.map.ObjectMapper;
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
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SignUpControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private static final ObjectMapper om = new ObjectMapper();

    @Test
    public void shouldSignUpNewUser() throws Exception {
        when(userService.signUp(any(SignUpFormData.class))).thenReturn(new UserAccountEntity());

        MvcResult mvcResult= mockMvc.perform(post("/api/v1/signup")
                .content(om.writeValueAsString(signUpFormData()))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
    }

    private SignUpFormData signUpFormData() {
        SignUpFormData signUpFormData = new SignUpFormData();
        signUpFormData.setFirstName("Name");
        signUpFormData.setLastName("Last");
        signUpFormData.setEmail("email@gmail.com");
        signUpFormData.setPassword("password");
        return signUpFormData;
    }
}
