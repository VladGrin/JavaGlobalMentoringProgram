package com.epam.spring.controller.auth;

import com.epam.spring.domain.UserAccountEntity;
import com.epam.spring.dto.AuthenticationRequestDto;
import com.epam.spring.security.JwtTokenProvider;
import com.epam.spring.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@PermitAll
@RequestMapping(value = "/api/v1/auth/")
@Slf4j
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;


    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequestDto requestDto) {
        try {
            String email = requestDto.getEmail();
            UserAccountEntity user = userService.findByEmail(email);
            if (user == null) {
                throw new UsernameNotFoundException("User with username: " + email + " not found");

            }
            List<GrantedAuthority> grantedAuthorityList = user.getRoles().stream().map(role ->
                    new SimpleGrantedAuthority(role.getName())
            ).collect(Collectors.toList());
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, requestDto.getPassword(), grantedAuthorityList));

            log.info(String.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal()));

            String token = jwtTokenProvider.createToken(email, user.getRoles());
            log.info("Granted token {} ", token);
            Map<Object, Object> response = new HashMap<>();
            response.put("username", email);
            response.put("token", token);
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
