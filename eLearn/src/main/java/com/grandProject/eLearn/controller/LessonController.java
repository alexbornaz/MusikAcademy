package com.grandProject.eLearn.controller;

import com.grandProject.eLearn.dto.request.LessonInfo;
import com.grandProject.eLearn.dto.response.MessageResponse;
import com.grandProject.eLearn.model.course.Course;
import com.grandProject.eLearn.model.lesson.Lesson;
import com.grandProject.eLearn.service.LessonService;
import com.grandProject.eLearn.service.course.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/lessons/")
public class LessonController {
    private final LessonService lessonService;
    private final CourseService courseService;

    public LessonController(LessonService lessonService, CourseService courseService) {
        this.lessonService = lessonService;
        this.courseService = courseService;
    }

    @GetMapping("{courseId}")
    public ResponseEntity<Set<Lesson>> getLessonsForCourse(@PathVariable long courseId) {
        Set<Lesson> lessons = lessonService.getAllLessonsForCourse(courseId);
        return ResponseEntity.ok().body(lessons);
    }

    @PostMapping("add/{courseId}")
    public ResponseEntity<?> addLessonToCourse(@PathVariable long courseId, @RequestBody LessonInfo lessonInfo) {
        try {
            Course course = courseService.getValidCourseById(courseId);
            lessonService.addLessonToCourse(course, lessonInfo);
            return ResponseEntity.ok().body(new MessageResponse("lesson added"));
        } catch (Exception e) {
            return ResponseEntity.ok().body("Something went wrong");
        }
    }

    @DeleteMapping("delete/{lessonId}")
    public ResponseEntity<?> deleteLesson(@PathVariable long lessonId){
        lessonService.deleteLesson(lessonId);
        return ResponseEntity.ok().body(new MessageResponse("Lesson deleted!"));
    }
}
