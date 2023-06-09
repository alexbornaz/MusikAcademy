package com.grandProject.eLearn.service.user;

import com.grandProject.eLearn.dto.request.MentorApplicationDTO;
import com.grandProject.eLearn.model.User;

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

    void applyMentor(MentorApplicationDTO mentorApplicationDTO);
    void makeMentor(String username);


}
