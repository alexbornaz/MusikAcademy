package com.grandProject.eLearn.repository;

import com.grandProject.eLearn.model.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsletterRepository extends JpaRepository<Subscriber, Long> {
    Subscriber findByEmail(String email);
    boolean existsByEmail(String email);
    void deleteByEmail(String email);
}
