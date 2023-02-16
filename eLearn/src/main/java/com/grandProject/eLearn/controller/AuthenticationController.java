package com.grandProject.eLearn.controller;


import com.grandProject.eLearn.dto.request.LoginRequest;
import com.grandProject.eLearn.dto.request.SignUpRequest;
import com.grandProject.eLearn.dto.response.MessageResponse;
import com.grandProject.eLearn.service.EmailSenderService;
import com.grandProject.eLearn.service.auth.UserAuthService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    private final UserAuthService userAuthService;
    private final EmailSenderService emailSenderService;

    public AuthenticationController(UserAuthService userAuthService, EmailSenderService emailSenderService) {
        this.userAuthService = userAuthService;
        this.emailSenderService = emailSenderService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> signUp(@RequestBody SignUpRequest signUpRequest){
        if (userAuthService.isValidUsername(signUpRequest.getUsername())) {
            return ResponseEntity.ok().body(new MessageResponse("Username already exists."));
        }
        if (userAuthService.isValidEmail(signUpRequest.getEmailAddress())) {
            return ResponseEntity.ok().body(new MessageResponse("Email already exists"));
        }
        userAuthService.saveUser(signUpRequest);
        String token = userAuthService.authenticateAndGetToken(signUpRequest.getUsername(),signUpRequest.getPassword());
        try {
            emailSenderService.sendRegistrationEmailFromApp(signUpRequest.getEmailAddress(),signUpRequest.getUsername());
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, token).build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        if (userAuthService.checkCredentials(loginRequest)) {
            String token = userAuthService.authenticateAndGetToken(loginRequest.getUsername(), loginRequest.getPassword());
            return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, token).build();
        }
        return ResponseEntity.ok().body(new MessageResponse("Invalid username or password"));
    }





}
