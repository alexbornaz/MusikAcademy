package com.grandProject.eLearn.controller;

import com.grandProject.eLearn.dto.response.MessageResponse;
import com.grandProject.eLearn.model.User;
import com.grandProject.eLearn.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/")
@Slf4j
public class AdminController {
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("allUsers")
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            List<User> users = userService.getUsers();
            log.info("Admin request for users list");
            return ResponseEntity.ok().body(users);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("get/{username}")
    public ResponseEntity<User> getUser(@PathVariable String username) {
        try {
            User user = userService.validateAndGetUserByUsername(username);
            log.info("Admin request for user {}", username);
            return ResponseEntity.ok().body(user);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("makeMentor/{username}")
    public ResponseEntity<MessageResponse> makeMentor(@PathVariable String username) {
        try {
            userService.makeMentor(username);
            log.info("Admin appointed user {} as mentor", username);
            return ResponseEntity.ok().body(new MessageResponse("User " + username + " assigned as mentor"));
        } catch (Exception e) {
            log.error("Mentor appointment bad request");
            return ResponseEntity.badRequest().body(new MessageResponse("Something went wrong check logs"));
        }
    }

    @DeleteMapping("{username}/delete")
    public ResponseEntity<MessageResponse> deleteUser(@PathVariable String username) {
        try {
            User user = userService.validateAndGetUserByUsername(username);
            userService.deleteUser(user);
            log.info("Admin request to delete user {}", username);
            return ResponseEntity.ok().body(new MessageResponse("User deleted"));
        } catch (Exception e) {
            log.error("Bad request to delete user {}", username);
            return ResponseEntity.badRequest().body(new MessageResponse("Could not delete user"));
        }
    }
}
