package com.grandProject.eLearn.repository;

import com.grandProject.eLearn.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmailAddress(String emailAddress);

    Optional<User> findByEmailAddress(String email);

}
