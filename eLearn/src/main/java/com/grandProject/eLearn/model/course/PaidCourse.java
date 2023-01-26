package com.grandProject.eLearn.model.course;

import com.grandProject.eLearn.model.lesson.Lesson;
import com.grandProject.eLearn.model.user.Mentor;

import java.math.BigDecimal;
import java.util.List;

public class PaidCourse extends Course{
    private BigDecimal price;
    public PaidCourse(String title, String description, List<Lesson> lessons, Mentor creator,BigDecimal price) {
        super(title, description, lessons, creator);
        this.price=price;
    }
}
