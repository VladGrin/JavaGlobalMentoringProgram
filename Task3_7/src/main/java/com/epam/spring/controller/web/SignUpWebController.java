package com.epam.spring.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.security.PermitAll;

@Controller
@PermitAll
public class SignUpWebController {

    @GetMapping("/signup")
    public String signUpPage() {
        return "sign-up";
    }
}
