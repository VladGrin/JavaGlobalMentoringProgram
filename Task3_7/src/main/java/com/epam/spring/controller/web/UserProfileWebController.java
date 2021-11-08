package com.epam.spring.controller.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@PreAuthorize("isAuthenticated()")
@RequestMapping("/profile")
@Slf4j
public class UserProfileWebController {

    @GetMapping
    public String openUserProfilePage() {
        log.info(String.valueOf(SecurityContextHolder.getContext().getAuthentication().getCredentials()));
        log.info(String.valueOf(SecurityContextHolder.getContext().getAuthentication().getAuthorities()));
        log.info(String.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
        log.info("User openUserProfilePage");
        return "my-account";
    }

    @GetMapping("/edit")
    public String updatePerson() {
        log.info("Controller updatePerson");
        return "my-account";
    }

}
