package com.grandProject.eLearn.controller;

import com.grandProject.eLearn.dto.request.LessonInfo;
import com.grandProject.eLearn.dto.response.MessageResponse;
import com.grandProject.eLearn.model.course.Course;
import com.grandProject.eLearn.model.lesson.Lesson;
import com.grandProject.eLearn.service.LessonService;
import com.grandProject.eLearn.service.course.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/lessons/")
@Slf4j
public class LessonController {
    private final LessonService lessonService;
    private final CourseService courseService;

    public LessonController(LessonService lessonService, CourseService courseService) {
        this.lessonService = lessonService;
        this.courseService = courseService;
    }

    @GetMapping("{courseId}")
    public ResponseEntity<Set<Lesson>> getLessonsForCourse(@PathVariable long courseId) {
        log.info("Request for all lessons of course with id {}", courseId);
        Set<Lesson> lessons = lessonService.getAllLessonsForCourse(courseId);
        return ResponseEntity.ok().body(lessons);
    }

    @PostMapping("add/{courseId}")
    public ResponseEntity<?> addLessonToCourse(@PathVariable long courseId, @RequestBody LessonInfo lessonInfo) {
        try {
            Course course = courseService.getValidCourseById(courseId);
            lessonService.addLessonToCourse(course, lessonInfo);
            log.info("Creating new lesson with name '{}' for course with id {}",lessonInfo.getTitle(),courseId);
            return ResponseEntity.ok().body(new MessageResponse("lesson added"));
        } catch (Exception e) {
            log.error("Lesson with name '{}' for course with id {} could not be created",lessonInfo.getTitle(),courseId);
            return ResponseEntity.ok().body("Something went wrong");
        }
    }

    @DeleteMapping("delete/{lessonId}")
    public ResponseEntity<?> deleteLesson(@PathVariable long lessonId){
        lessonService.deleteLesson(lessonId);
        log.info("Deleting lesson with id {}",lessonId);
        return ResponseEntity.ok().body(new MessageResponse("Lesson deleted!"));
    }
}
