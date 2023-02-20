package com.grandProject.eLearn.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String reviewText;
    @ManyToOne
    @JoinColumn(name="course_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    Course course;
    @ManyToOne
    @JoinColumn(name="user_username",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    User owner;

    public Review(String reviewText, Course course, User owner) {
        this.reviewText = reviewText;
        this.course = course;
        this.owner = owner;
    }

    public Review() {

    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
