package com.grandProject.eLearn.repository;

import com.grandProject.eLearn.model.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course,Long> {
}
