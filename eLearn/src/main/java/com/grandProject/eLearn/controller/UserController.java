package com.grandProject.eLearn.controller;

import com.grandProject.eLearn.dto.request.MentorApplicationDTO;
import com.grandProject.eLearn.dto.response.MessageResponse;
import com.grandProject.eLearn.model.Course;
import com.grandProject.eLearn.model.User;
import com.grandProject.eLearn.service.course.CourseService;
import com.grandProject.eLearn.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
@RequestMapping("/api/user/")
@Slf4j
public class UserController {
    private final UserService userService;
    private final CourseService courseService;

    public UserController(UserService userService, CourseService courseService) {
        this.userService = userService;
        this.courseService = courseService;
    }


    @GetMapping("{username}/enrolled")
    public ResponseEntity<?> getEnrolledCourses(@PathVariable String username) {
        User user = userService.validateAndGetUserByUsername(username);
        return ResponseEntity.ok().body(user.getEnrolledCourses());
    }

    @GetMapping("{username}/enrolled/ids")
    public ResponseEntity<?> getEnrolledIds(@PathVariable String username) {
        User user = userService.validateAndGetUserByUsername(username);
        ArrayList courseIdList = userService.getEnrolledCoursesIds(user);
        return ResponseEntity.ok().body(courseIdList);
    }


    @PutMapping("{username}/addCourse/{courseId}")
    public ResponseEntity<?> addCourse(@PathVariable String username, @PathVariable Long courseId) {
        User user = userService.validateAndGetUserByUsername(username);
        Course course = courseService.getValidCourseById(courseId);
        user.addCourse(course);
        userService.saveUser(user);
        return ResponseEntity.ok().body(new MessageResponse("Course added"));
    }

    @PostMapping("applyMentor")
    public ResponseEntity<MessageResponse> applyMentor(@RequestBody MentorApplicationDTO mentorApplicationDTO) {
        try {
            userService.applyMentor(mentorApplicationDTO);
            return ResponseEntity.ok().body(new MessageResponse("You applied with success,you will receive a notification when is approved"));
        } catch (Exception e) {
            log.error("Request to apply to become mentor from {} failed", mentorApplicationDTO.getUsername());
            return ResponseEntity.ok().body(new MessageResponse("Something went wrong with you application, try gain later"));
        }
    }
}
