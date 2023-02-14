package com.grandProject.eLearn.controller;

import com.grandProject.eLearn.dto.response.MessageResponse;
import com.grandProject.eLearn.model.course.Course;
import com.grandProject.eLearn.model.user.User;
import com.grandProject.eLearn.service.course.CourseService;
import com.grandProject.eLearn.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user/")
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

    @PutMapping("{username}/addCourse/{courseId}")
    public ResponseEntity<?> addCourse(@PathVariable String username, @PathVariable Long courseId) {
        User user = userService.validateAndGetUserByUsername(username);
        Course course = courseService.getValidCourseById(courseId);
        user.addCourse(course);
        userService.saveUser(user);
        return ResponseEntity.ok().body(new MessageResponse("Course added"));
    }
}
