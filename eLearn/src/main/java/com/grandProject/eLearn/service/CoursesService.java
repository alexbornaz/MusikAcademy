package com.grandProject.eLearn.service;

import com.grandProject.eLearn.dao.CourseDAO;
import com.grandProject.eLearn.model.course.Course;

import com.grandProject.eLearn.model.lesson.Lesson;
import com.grandProject.eLearn.model.user.Mentor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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

    private long createId(){
        return courseDAO.getAll().size()+1;
    }


    public void createCourse(Course courseToCreate) {
        Course newCourse = new Course(courseToCreate.getTitle(),courseToCreate.getDescription(),courseToCreate.getCreator());
        newCourse.setId(createId());
        courseDAO.save(newCourse);
    }

    public boolean deleteCourse(long courseId) {
        return courseDAO.deleteCourse(courseId);
    }

    public boolean updateCourse(long id,Course updatedCourse) {
        return courseDAO.update(id,updatedCourse);
    }
}
