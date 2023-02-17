package com.grandProject.eLearn.controller;

import com.grandProject.eLearn.dto.request.CourseDTO;
import com.grandProject.eLearn.dto.response.CreatedCourseMessage;
import com.grandProject.eLearn.model.course.Course;
import com.grandProject.eLearn.service.course.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/course")
@Slf4j
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Course>> getAll() {
        List<Course> courses = courseService.getCourses();
        log.info("Requested all courses from database");
        return ResponseEntity.ok().body(courses);
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<?> getCourse(@PathVariable Long courseId) {
        Optional<Course> course = courseService.getCourseById(courseId);
        log.info("Requested the course with id:{}",courseId);
        if (courseService.exists(courseId)) {
            log.info("Course with id {} is returned as response",courseId);
            return ResponseEntity.ok().body(course);
        }
        log.info("Course with id {} could not be found",courseId);
        return ResponseEntity.notFound().build();
    }

    @PostMapping(value = "/create")
    public ResponseEntity<?> createCourse(@RequestBody CourseDTO courseInfo) {
        Long id = courseService.saveCourse(courseInfo);
        log.info("Request for new course with name {} and owner {}",courseInfo.getTitle(),courseInfo.getCreatorUsername());
        return ResponseEntity.ok().body(new CreatedCourseMessage("success", id));
    }
}
