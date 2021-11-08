package com.epam.spring.controller.auth;

import com.epam.spring.domain.UserAccountEntity;
import com.epam.spring.repository.UserAccountRepository;
import com.epam.spring.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final UserAccountRepository userAccountRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public OAuth2AuthenticationSuccessHandler(UserAccountRepository userAccountRepository,
                                              AuthenticationManager authenticationManager,
                                              JwtTokenProvider jwtTokenProvider) {
        this.userAccountRepository = userAccountRepository;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        OidcUser oidcUser = (OidcUser) authentication.getPrincipal();
        Map<String, Object> attributes = oidcUser.getAttributes();
        String email = (String) attributes.get("email");
        UserAccountEntity user = userAccountRepository.findByEmail(email);

        List<GrantedAuthority> grantedAuthorityList = user.getRoles().stream().map(role ->
                new SimpleGrantedAuthority(role.getName())
        ).collect(Collectors.toList());

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, attributes.get("sub"), grantedAuthorityList));

        String token = jwtTokenProvider.createToken(email, user.getRoles());

        getRedirectStrategy().sendRedirect(request, response, "http://localhost:8080/profile?username=" + email + "&token=" + token);
    }
}