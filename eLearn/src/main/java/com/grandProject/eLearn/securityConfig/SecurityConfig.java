package com.grandProject.eLearn.securityConfig;


import com.grandProject.eLearn.securityConfig.oauth2.CustomAuthenticationSuccessHandler;
import com.grandProject.eLearn.securityConfig.oauth2.CustomOAuth2UserService;
import com.grandProject.eLearn.securityConfig.oauth2.HttpCookieOAuth2AuthorizationRequestRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final TokenAuthenticationFilter tokenAuthenticationFilter;
    private final CustomOAuth2UserService customOAuth2UserService;
    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    public SecurityConfig(TokenAuthenticationFilter tokenAuthenticationFilter, CustomOAuth2UserService customOAuth2UserService, CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler) {
        this.tokenAuthenticationFilter = tokenAuthenticationFilter;
        this.customOAuth2UserService = customOAuth2UserService;
        this.customAuthenticationSuccessHandler = customAuthenticationSuccessHandler;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public HttpCookieOAuth2AuthorizationRequestRepository cookieAuthorizationRequestRepository() {
        return new HttpCookieOAuth2AuthorizationRequestRepository();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST, "/api/course/create").hasAuthority("mentor")
                .requestMatchers("/api/auth/*", "/oauth2/**", "/api/course/*", "/api/newsletter/**").permitAll()
                .anyRequest().authenticated();
        http.oauth2Login().authorizationEndpoint().baseUri("/oauth2/authorize")
                .authorizationRequestRepository(cookieAuthorizationRequestRepository()).and()
                .redirectionEndpoint()
                .baseUri("/oauth2/callback/*")
                .and()
                .userInfoEndpoint()
                .userService(customOAuth2UserService)
                .and()
                .successHandler(customAuthenticationSuccessHandler);
        http.addFilterBefore(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        http.exceptionHandling(e -> e.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)));
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.cors().and().csrf().disable();
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

