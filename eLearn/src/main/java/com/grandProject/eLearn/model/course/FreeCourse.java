package com.grandProject.eLearn.model.course;

import com.grandProject.eLearn.model.lesson.Lesson;
import com.grandProject.eLearn.model.user.Mentor;

import java.util.List;

public class FreeCourse extends Course{
    public FreeCourse(String title, String description, List<Lesson> lessons, Mentor creator) {
        super(title, description, lessons, creator);
    }
}
