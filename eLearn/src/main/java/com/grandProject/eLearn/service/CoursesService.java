package com.grandProject.eLearn.service;

import com.grandProject.eLearn.dao.CourseDAO;
import com.grandProject.eLearn.model.course.Course;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Set;

@Service
public class CoursesService{
    private final CourseDAO courseDAO;

    public CoursesService(CourseDAO courseDAO) {

        this.courseDAO = courseDAO;
    }

    public Set<Course> getAllCourses() {
        return courseDAO.getAll();
    }


    public void createCourse(Course courseToCreate) {
//        Course newCourse = new Course(courseToCreate.getTitle(),courseToCreate.getDescription(),courseToCreate.getCreator());
//        courseDAO.save(newCourse);
    }

    public boolean deleteCourse(long courseId) {
        return courseDAO.deleteCourse(courseId);
    }

    public boolean updateCourse(long id,Course updatedCourse) {
        return courseDAO.update(id,updatedCourse);
    }
}
