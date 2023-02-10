package com.grandProject.eLearn.repository;

import com.grandProject.eLearn.model.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course,Long> {
    boolean existsById(Long id);
}
