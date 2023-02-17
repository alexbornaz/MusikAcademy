package com.grandProject.eLearn.service.user;

import com.grandProject.eLearn.model.course.Course;
import com.grandProject.eLearn.model.user.User;
import com.grandProject.eLearn.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean hasUserWithUsername(String username) {
        return userRepository.existsByUsername(username);
    }



    @Override
    public boolean hasUserWithEmail(String email) {
        return userRepository.existsByEmailAddress(email);
    }

    @Override
    public User validateAndGetUserByUsername(String username) {
        return getUserByUsername(username).orElseThrow(()->new UsernameNotFoundException(String.format("User with username %s not found", username)));
    }



    @Override
    public User saveUser(User user) {
        log.info("Created user with username {}",user.getUsername());
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(User user) {
        log.info("Deleted user with username {}",user.getUsername());
        userRepository.delete(user);

    }

    @Override
    public ArrayList<Long> getEnrolledCoursesIds(User user) {
        ArrayList<Long> idList = new ArrayList<>();
        user.getEnrolledCourses().forEach(course ->idList.add(course.getId()));
        return idList;
    }


}
