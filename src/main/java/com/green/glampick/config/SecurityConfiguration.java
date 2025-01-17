package com.green.glampick.config;

import com.green.glampick.jwt.JwtAuthenticationAccessDeniedHandler;
import com.green.glampick.jwt.JwtAuthenticationEntryPoint;
import com.green.glampick.jwt.JwtAuthenticationFilter;
import com.green.glampick.oauth2.OAuth2AuthenticationFailureHandler;
import com.green.glampick.oauth2.OAuth2AuthenticationRequestBasedOnCookieRepository;
import com.green.glampick.oauth2.OAuth2AuthenticationSuccessHandler;
import com.green.glampick.service.implement.SocialLoginServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final OAuth2AuthenticationRequestBasedOnCookieRepository repository;
    private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
    private final OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;
    private final SocialLoginServiceImpl service;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity.sessionManagement(
                session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(http -> http.disable())
                .formLogin(form -> form.disable())
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(
                        auth -> auth.requestMatchers(
                                        "api/user**"
                                        , "api/user/**"

                                ).authenticated()
                                .anyRequest().permitAll()
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(exception -> exception.authenticationEntryPoint(new JwtAuthenticationEntryPoint())
                        .accessDeniedHandler(new JwtAuthenticationAccessDeniedHandler())
                )
//                .oauth2Login( oauth2 -> oauth2.authorizationEndpoint(
//                                        auth -> auth.baseUri("/oauth2/authorization")
//                                                .authorizationRequestRepository(repository))
//                                .redirectionEndpoint( redirection -> redirection.baseUri("/*/oauth2/code/*"))
//                                .userInfoEndpoint(userInfo -> userInfo.userService(service))
//                                .successHandler(oAuth2AuthenticationSuccessHandler)
//                                .failureHandler(oAuth2AuthenticationFailureHandler)
//                )
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
