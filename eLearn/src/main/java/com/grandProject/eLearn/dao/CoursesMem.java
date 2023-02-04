package com.grandProject.eLearn.dao;

import com.grandProject.eLearn.model.course.Course;
import org.springframework.stereotype.Repository;

import java.util.Set;
//class for demo purposes only before database is implemented
@Repository
public class CoursesMem implements CourseDAO {
    private final Set<Course> courses;

    public CoursesMem(Set<Course> courses) {
        this.courses = courses;
    }

    @Override
    public Set<Course> getAll() {
        return courses;
    }

    @Override
    public void save(Course course) {
        course.setId(courses.size()+1);
        courses.add(course);
    }

    @Override
    public boolean update(long id,Course course) {
        Course originalCourse= courses.stream()
                .filter(defCourse ->defCourse.getId()== id)
                .findFirst()
                .orElse(null);
        if(originalCourse != null){
            originalCourse.setTitle(course.getTitle());
            originalCourse.setDescription(course.getDescription());
            originalCourse.setDefaultPrice(course.getDefaultPrice());
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteCourse(long courseId) {
        return courses.removeIf(course -> course.getId() == courseId);
    }
}
