package com.grandProject.eLearn.model.course;


import com.grandProject.eLearn.model.lesson.Lesson;
import com.grandProject.eLearn.model.user.Mentor;

import java.util.List;

public abstract class Course {
    private String title;
    private String description;
    private List<Lesson> lessons;
    private Mentor creator;


    public Course(String title, String description, List<Lesson> lessons, Mentor creator) {
        this.title = title;
        this.description = description;
        this.lessons = lessons;
        this.creator = creator;
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

    public void setCreator(Mentor creator) {
        this.creator = creator;
    }
}
