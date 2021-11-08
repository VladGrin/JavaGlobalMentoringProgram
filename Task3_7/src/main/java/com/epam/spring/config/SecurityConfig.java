package com.epam.spring.config;

import com.epam.spring.controller.auth.OAuth2AuthenticationSuccessHandler;
import com.epam.spring.security.JwtConfigurer;
import com.epam.spring.security.JwtTokenProvider;
import com.epam.spring.service.impl.CustomOidcUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public SecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Autowired
    private CustomOidcUserService customOidcUserService;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    private OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                    .csrf().disable()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .authorizeRequests()
                    .antMatchers("login/oauth2/code/google").permitAll()
                .and()
                    .logout()
                    .invalidateHttpSession(true)
                .and()
                    .apply(new JwtConfigurer(jwtTokenProvider))
                .and()
                    .oauth2Login()
                    .loginPage("/login")
                    .redirectionEndpoint()
                    .baseUri("/oauth2/callback/*")
                .and()
                    .userInfoEndpoint()
                    .oidcUserService(customOidcUserService)
                .and()
                    .successHandler(oAuth2AuthenticationSuccessHandler);
    }
}
