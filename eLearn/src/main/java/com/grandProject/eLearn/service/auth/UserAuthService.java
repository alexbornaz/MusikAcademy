package com.grandProject.eLearn.service.auth;

import com.grandProject.eLearn.dto.response.MessageResponse;
import com.grandProject.eLearn.model.User;
import com.grandProject.eLearn.dto.request.LoginRequest;
import com.grandProject.eLearn.dto.request.SignUpRequest;
import com.grandProject.eLearn.securityConfig.TokenProvider;
import com.grandProject.eLearn.service.EmailSenderService;
import com.grandProject.eLearn.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserAuthService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;
    private final EmailSenderService emailSenderService;

    public UserAuthService(UserService userService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, TokenProvider tokenProvider, EmailSenderService emailSenderService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
//        this.userService.saveUser(new User("admin", passwordEncoder.encode("Cantar12."), "musikacademytest@gmail.com", "adminescu", "adminul",new HashSet<>(Arrays.asList("ADMIN"))));
        this.emailSenderService = emailSenderService;
    }

    public User mapSignUpRequestToUser(SignUpRequest signUpRequest) {
        User user = new User();
        user.setUsername(signUpRequest.getUsername());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setFirstName(signUpRequest.getFirstName());
        user.setLastName(signUpRequest.getLastName());
        user.setEmailAddress(signUpRequest.getEmailAddress());
        user.addRole("USER");
        return user;
    }

    public String authenticateAndGetToken(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        log.info("{} has authenticated", username);
        return tokenProvider.generate(authentication);
    }

    public boolean isValidUsername(String username) {
        return userService.hasUserWithUsername(username);
    }

    public boolean isValidEmail(String email) {
        return userService.hasUserWithEmail(email);
    }

    public void saveUser(SignUpRequest signUpRequest) {
        User user = mapSignUpRequestToUser(signUpRequest);
        try {
            userService.saveUser(user);
            log.info("Created user with username {}", signUpRequest.getUsername());
        } catch (Exception e) {
            log.info("Could not save user with username {}",signUpRequest.getUsername());
        }
    }

    public boolean checkCredentials(LoginRequest loginRequest) {
        if (userService.hasUserWithUsername(loginRequest.getUsername())) {
            String encodedPass = userService.getUserByUsername(loginRequest.getUsername()).get().getPassword();
            return passwordEncoder.matches(loginRequest.getPassword(), encodedPass);
        }
        log.warn("Login attempt from {} resulted unsuccessful", loginRequest.getUsername());
        return false;
    }

    public String registerUser(SignUpRequest signUpRequest) {
        if (isValidUsername(signUpRequest.getUsername())) {
            log.warn("{}, username already exists, registration failed", signUpRequest.getUsername());
            throw new RuntimeException("Username already exists.");
        }
        if (isValidEmail(signUpRequest.getEmailAddress())) {
            log.warn("{}, email already exists, registration failed", signUpRequest.getEmailAddress());
            throw new RuntimeException("Email already exists");
        }
        saveUser(signUpRequest);
        String token = authenticateAndGetToken(signUpRequest.getUsername(), signUpRequest.getPassword());
        try {
            emailSenderService.sendRegistrationEmailFromApp(signUpRequest.getEmailAddress(), signUpRequest.getUsername());
        } catch (Exception e) {
            log.error("could not send registration email to {}", signUpRequest.getEmailAddress());
        }
        return token;
    }

    public String signInUser(LoginRequest loginRequest) {
        if (checkCredentials(loginRequest)) {
            String token = authenticateAndGetToken(loginRequest.getUsername(), loginRequest.getPassword());
            return token;
        } else {
            throw new RuntimeException("Username or Password wrong");
        }
    }
}
