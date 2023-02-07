package com.grandProject.eLearn.controller;

import com.grandProject.eLearn.dto.request.CourseDTO;
import com.grandProject.eLearn.dto.response.CreatedCourseMessage;
import com.grandProject.eLearn.dto.response.MessageResponse;
import com.grandProject.eLearn.model.course.Course;
import com.grandProject.eLearn.service.course.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Course>> getAll(){
        List<Course> courses = courseService.getCourses();
        return ResponseEntity.ok().body(courses);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createCourse(@RequestBody CourseDTO courseInfo){
            Long id = courseService.saveCourse(courseInfo);
        System.out.println(id);
            return ResponseEntity.ok().body(new CreatedCourseMessage("success",id));
    }
}
