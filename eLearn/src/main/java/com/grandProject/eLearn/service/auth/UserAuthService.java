package com.grandProject.eLearn.service.auth;

import com.grandProject.eLearn.model.user.User;
import com.grandProject.eLearn.dto.request.LoginRequest;
import com.grandProject.eLearn.dto.request.SignUpRequest;
import com.grandProject.eLearn.securityConfig.TokenProvider;
import com.grandProject.eLearn.service.user.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserAuthService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;

    public UserAuthService(UserService userService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, TokenProvider tokenProvider) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
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
        return tokenProvider.generate(authentication);
    }
    public boolean isValidUsername(String username){
        return userService.hasUserWithUsername(username);
    }
    public  boolean isValidEmail(String email){
        return userService.hasUserWithEmail(email);
    }
    public void saveUser(SignUpRequest signUpRequest){
        User user = mapSignUpRequestToUser(signUpRequest);
        userService.saveUser(user);
    }

    public boolean checkCredentials(LoginRequest loginRequest) {
        if (userService.hasUserWithUsername(loginRequest.getUsername())){
           String encodedPass = userService.getUserByUsername(loginRequest.getUsername()).get().getPassword();
           return passwordEncoder.matches(loginRequest.getPassword(),encodedPass);
        }
        return false;
    }
}
