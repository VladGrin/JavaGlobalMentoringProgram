package com.epam.spring.controller.rest;

import com.epam.spring.domain.UserAccountEntity;
import com.epam.spring.dto.form.user.SignUpFormData;
import com.epam.spring.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;

@RestController
@AllArgsConstructor
@Slf4j
public class SignUpController {

    private UserService userService;

    @PermitAll
    @PostMapping(path = "/api/v1/signup")
    public ResponseEntity<UserAccountEntity> signUp(@RequestBody SignUpFormData formData) {
        userService.signUp(formData);
        log.info("User register.");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}