package com.grandProject.eLearn.service.user;

import com.grandProject.eLearn.dto.request.MentorApplicationDTO;
import com.grandProject.eLearn.model.User;
import com.grandProject.eLearn.repository.UserRepository;
import com.grandProject.eLearn.service.EmailSenderService;
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
    private final EmailSenderService emailSenderService;

    public UserServiceImpl(UserRepository userRepository, EmailSenderService emailSenderService) {
        this.userRepository = userRepository;
        this.emailSenderService = emailSenderService;
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

    @Override
    public void applyMentor(MentorApplicationDTO mentorApplicationDTO) {
        if (userRepository.existsByUsername(mentorApplicationDTO.getUsername())){
            User user = validateAndGetUserByUsername(mentorApplicationDTO.getUsername());
            System.out.println(user.getEmailAddress()+"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
            emailSenderService.sendMentorApplication(user.getEmailAddress(),mentorApplicationDTO.getApplication());
            log.info("Application from {} sent",mentorApplicationDTO.getUsername());
        }else {
            log.error("Application from {} not sent",mentorApplicationDTO.getUsername());
        }
    }

    @Override
    public void makeMentor(String username) {
        if (userRepository.existsByUsername(username)){
            User user = validateAndGetUserByUsername(username);
            if (user.getRoles().contains("mentor")){
                throw  new RuntimeException("user already mentor");
            }else{
                user.addRole("mentor");
                userRepository.save(user);
            }
        }
    }




}
