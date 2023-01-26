package com.grandProject.eLearn.model.course;


import com.grandProject.eLearn.model.lesson.Lesson;
import com.grandProject.eLearn.model.user.Mentor;
;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;




public class Course {
    private long id;

    private String title;

    private String description;
    private List<Lesson> lessons;
    private Mentor creator;
    private BigDecimal defaultPrice=BigDecimal.ZERO;

    public Course(String title, String description, Mentor creator) {
        this.title = title;
        this.description = description;
        this.lessons = new ArrayList<>();
        this.creator = creator;

    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public Mentor getCreator() {
        return creator;
    }


    public BigDecimal getDefaultPrice() {
        return defaultPrice;
    }

    public void setDefaultPrice(BigDecimal defaultPrice) {
        this.defaultPrice = defaultPrice;
    }
}
