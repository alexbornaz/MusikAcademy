package com.grandProject.eLearn.service;

import com.grandProject.eLearn.model.user.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
public interface UserService {

    List<User> getUsers();

    Optional<User> getUserByUsername(String username);

    boolean hasUserWithUsername(String username);

    boolean hasUserWithEmail(String email);

    User validateAndGetUserByUsername(String username);

    User saveUser(User user);

    void deleteUser(User user);
}