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
        try {
            String token = userAuthService.registerUser(signUpRequest);
            return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, token).build();
        }catch (RuntimeException e){
            return ResponseEntity.ok().body(new MessageResponse(e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            String token = userAuthService.signInUser(loginRequest);
            return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, token).build();
        }catch (RuntimeException e){
            return ResponseEntity.ok().body(new MessageResponse("Invalid username or password"));
        }

    }





}
