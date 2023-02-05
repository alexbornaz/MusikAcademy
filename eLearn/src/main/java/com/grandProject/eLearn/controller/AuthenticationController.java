package com.grandProject.eLearn.controller;


import com.grandProject.eLearn.model.user.User;
import com.grandProject.eLearn.payload.request.LoginRequest;
import com.grandProject.eLearn.payload.request.SignUpRequest;
import com.grandProject.eLearn.payload.response.MessageResponse;
import com.grandProject.eLearn.securityConfig.TokenProvider;
import com.grandProject.eLearn.service.user.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;

    public AuthenticationController(UserService userService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, TokenProvider tokenProvider) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping("/register")
    public ResponseEntity<?> signUp(@RequestBody SignUpRequest signUpRequest){
        if (userService.hasUserWithUsername(signUpRequest.getUsername())) {
            return ResponseEntity.ok().body(new MessageResponse("Username already exists."));
        }
        if (userService.hasUserWithEmail(signUpRequest.getEmailAddress())) {
            return ResponseEntity.ok().body(new MessageResponse("Email already exists"));
        }
        User user = mapSignUpRequestToUser(signUpRequest);
        userService.saveUser(user);
        String token = authenticateAndGetToken(signUpRequest.getUsername(), signUpRequest.getPassword());
        System.out.println(token);
        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, token).build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        String token = authenticateAndGetToken(loginRequest.getUsername(), loginRequest.getPassword());
        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION,token).build();
    }

    private String authenticateAndGetToken(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        return tokenProvider.generate(authentication);
    }

    private User mapSignUpRequestToUser(SignUpRequest signUpRequest) {
        User user = new User();
        user.setUsername(signUpRequest.getUsername());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setFirstName(signUpRequest.getFirstName());
        user.setLastName(signUpRequest.getLastName());
        user.setEmailAddress(signUpRequest.getEmailAddress());
        user.setRole(signUpRequest.getRole());
        return user;
    }

}
