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
        Mentor mentor = new Mentor("alex", "bornaz",1);
        courseDAO.save(new Course("first course","description of first course",new ArrayList<>(),mentor));
        courseDAO.save(new Course("second course","description of second course",new ArrayList<>(),mentor));
        courseDAO.save(new Course("third course","description of third course",new ArrayList<>(),mentor));
    }

    public Set<Course> getAllCourses() {
        return courseDAO.getAll();
    }


    public void createCourse(Course courseToCreate) {
        Course newCourse = new Course(courseToCreate.getTitle(),courseToCreate.getDescription(),courseToCreate.getLessons(),courseToCreate.getCreator());
        courseDAO.save(newCourse);
    }

    public boolean deleteCourse(long courseId) {
        return courseDAO.deleteCourse(courseId);
    }

    public boolean updateCourse(long id,Course updatedCourse) {
        return courseDAO.update(id,updatedCourse);
    }
}
