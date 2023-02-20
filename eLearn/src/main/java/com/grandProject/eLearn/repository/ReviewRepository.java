package com.grandProject.eLearn.repository;

import com.grandProject.eLearn.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {
    boolean existsByCourse_IdAndOwner_Username(Long courseId,String username);
}
