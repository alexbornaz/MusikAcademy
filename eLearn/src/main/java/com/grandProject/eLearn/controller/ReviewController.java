package com.grandProject.eLearn.controller;

import com.grandProject.eLearn.dto.request.ReviewDto;
import com.grandProject.eLearn.dto.response.MessageResponse;
import com.grandProject.eLearn.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews/")
@Slf4j
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("add")
    public ResponseEntity<MessageResponse> addReview(@RequestBody ReviewDto reviewDto) {
        try {
            reviewService.addReview(reviewDto);
            return ResponseEntity.ok().body(new MessageResponse("Review added with success"));
        } catch (Exception e) {
            log.error("Could not add review to course with id {} from {}", reviewDto.getCourseId(), reviewDto.getUsername());
            return ResponseEntity.ok().body(new MessageResponse("Something went wrong, could not add review"));
        }
    }

    @DeleteMapping("delete/{reviewId}")
    public ResponseEntity<MessageResponse> deleteReview(@PathVariable Long reviewId) {
        try {
            reviewService.deleteReview(reviewId);
            return ResponseEntity.ok().body(new MessageResponse("Review deleted with success"));
        } catch (Exception e) {
            log.error("Could not delete review to course with id {}", reviewId);
            return ResponseEntity.ok().body(new MessageResponse("Something went wrong, could not delete review"));
        }
    }
}
