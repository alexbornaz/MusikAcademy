package com.grandProject.eLearn.service.course;

import com.grandProject.eLearn.dto.request.CourseDTO;
import com.grandProject.eLearn.model.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    List<Course> getCourses();

    Optional<Course> getCourseById(Long id);
    Course getValidCourseById(Long id);

    boolean exists(Long id);


    void saveCourse(Course course);

    Long saveCourse(CourseDTO courseDTO);

    List<Course> getTopCourses();

    void deleteCourse(Long courseId);
}
