package com.epam.spring.controller.rest;

import com.epam.spring.domain.UserAccountEntity;
import com.epam.spring.domain.UserProfileEntity;
import com.epam.spring.dto.form.user.UpdateUserProfileForm;
import com.epam.spring.service.UserService;
import com.epam.spring.service.impl.UserProfileServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping("/api/v1/profile")
@AllArgsConstructor
@Slf4j
public class UserProfileController {

    private UserProfileServiceImpl userProfileService;
    private UserService userService;

    @GetMapping("/{email}")
    public ResponseEntity<UserProfileEntity> getByEmail(@PathVariable String email) {

        UserAccountEntity userAccountEntity = userService.findByEmail(email);
        UserProfileEntity userProfileEntity = userAccountEntity.getUserProfileEntity();
        log.info("User getByEmail: " + userProfileEntity);
        return new ResponseEntity<>(userProfileEntity, HttpStatus.OK);
    }

    @PutMapping("/{email}")
    public ResponseEntity<UserProfileEntity> updateProfile(@PathVariable String email,
                                                           @RequestBody UpdateUserProfileForm form) {

        userProfileService.updateProfile(email, form);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
