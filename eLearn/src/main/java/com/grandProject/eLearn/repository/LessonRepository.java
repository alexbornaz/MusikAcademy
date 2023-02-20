package com.grandProject.eLearn.repository;

import com.grandProject.eLearn.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface LessonRepository extends JpaRepository<Lesson,Long> {
    Set<Lesson> findAllByCourse_Id(long courseId);
}
