package com.grandProject.eLearn.service;

import com.grandProject.eLearn.dto.request.ReviewDto;
import com.grandProject.eLearn.model.Course;
import com.grandProject.eLearn.model.Review;
import com.grandProject.eLearn.model.User;
import com.grandProject.eLearn.repository.ReviewRepository;
import com.grandProject.eLearn.service.course.CourseService;
import com.grandProject.eLearn.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final CourseService courseService;
    private final UserService userService;

    public ReviewService(ReviewRepository reviewRepository, CourseService courseService, UserService userService) {
        this.reviewRepository = reviewRepository;
        this.courseService = courseService;
        this.userService = userService;
    }

    public Review getValidReviewById(Long id) {
        return reviewRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Review not found"));
    }

    public void addReview(ReviewDto reviewDto) {
        if (reviewRepository.existsByCourse_IdAndOwner_Username(reviewDto.getCourseId(), reviewDto.getUsername())) {
            log.error("Review from {} for course with id {} already exists", reviewDto.getUsername(), reviewDto.getCourseId());
        } else {
            User user = userService.validateAndGetUserByUsername(reviewDto.getUsername());
            Course course = courseService.getValidCourseById(reviewDto.getCourseId());
            Review review = new Review(reviewDto.getReviewText(), course, user);
            course.addReview(review);
            reviewRepository.save(review);
            courseService.saveCourse(course);
        }
    }

    public void deleteReview(Long reviewId) {
        try {
            Review review = getValidReviewById(reviewId);
            Long courseId = review.getCourse().getId();
            Course course = courseService.getValidCourseById(courseId);
            course.deleteReview(review);
            reviewRepository.deleteById(reviewId);
        } catch (Exception e) {
            log.error("Could not delete review with id {}", reviewId);
        }
    }
}
