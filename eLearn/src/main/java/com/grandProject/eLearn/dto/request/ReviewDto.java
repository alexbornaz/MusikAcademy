package com.grandProject.eLearn.dto.request;

public class ReviewDto {
    private Long courseId;
    private String username;
    private String reviewText;

    public ReviewDto(Long courseId, String username, String reviewText) {
        this.courseId = courseId;
        this.username = username;
        this.reviewText = reviewText;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }
}
