package com.grandProject.eLearn.dao;

import com.grandProject.eLearn.model.course.Course;
import org.springframework.stereotype.Component;

import java.util.Set;
@Component
public interface CourseDAO {
    Set<Course> getAll();
    void save(Course course);
    boolean update(long id,Course course);
    boolean deleteCourse(long id);
}
