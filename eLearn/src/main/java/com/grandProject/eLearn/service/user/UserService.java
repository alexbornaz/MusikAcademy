package com.grandProject.eLearn.service.user;

import com.grandProject.eLearn.model.course.Course;
import com.grandProject.eLearn.model.user.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
public interface UserService {

    List<User> getUsers();

    Optional<User> getUserByUsername(String username);

    boolean hasUserWithUsername(String username);


    boolean hasUserWithEmail(String email);

    User validateAndGetUserByUsername(String username);


    User saveUser(User user);

    void deleteUser(User user);

    ArrayList<Long> getEnrolledCoursesIds(User user);
}
