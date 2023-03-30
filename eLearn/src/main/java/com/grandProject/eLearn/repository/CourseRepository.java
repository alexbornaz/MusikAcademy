package com.grandProject.eLearn.repository;

import com.grandProject.eLearn.model.Course;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    boolean existsById(Long id);

    //    @Query("""
//            SELECT course.id,course.title,course.description,course.image, COUNT(users_enrolled_courses.user_id) AS num_enrolled
//            FROM course
//            JOIN users_enrolled_courses
//            ON course.id = users_enrolled_courses.enrolled_courses_id
//            GROUP BY Course.id
//            ORDER BY num_enrolled DESC
//            LIMIT 5""")
    @Query("select c from Course  c order by SIZE(c.signedUsers) DESC ")
    List<Course> getTop(Pageable pageable);
}
