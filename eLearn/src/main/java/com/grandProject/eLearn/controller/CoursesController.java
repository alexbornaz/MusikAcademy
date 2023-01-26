package com.grandProject.eLearn.controller;

import com.grandProject.eLearn.model.course.Course;
import com.grandProject.eLearn.service.CoursesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/courses")
public class CoursesController {
    private final CoursesService courseService;

    public CoursesController(CoursesService courseService) {
        this.courseService = courseService;
    }


    @GetMapping
    public ResponseEntity<Set<Course>> getAll(){
        Set<Course> courses = courseService.getAllCourses();
        System.out.println(courses.toString());
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }
    @PostMapping(path = "/initialize")
    public ResponseEntity<String> initializeCourse(@RequestBody Course courseToCreate){
        courseService.createCourse(courseToCreate);
        return new ResponseEntity("Course created successfully", HttpStatus.OK);
    }
    @PutMapping(path = "/edit/{courseId}")
    public ResponseEntity<String> editCourse(@PathVariable long courseId,@RequestBody Course updatedCourse){
        if(courseService.updateCourse(courseId,updatedCourse)){
            return new ResponseEntity<>("Course updated successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>("Couldn't update course", HttpStatus.BAD_REQUEST);
    }
    @DeleteMapping(path = "/delete/{courseId}")
    public ResponseEntity<String> deleteCourse(@PathVariable long courseId){
        if (courseService.deleteCourse(courseId)){
            return new  ResponseEntity<>("Course removed successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>("Couldn't delete course", HttpStatus.BAD_REQUEST);
    }
}
