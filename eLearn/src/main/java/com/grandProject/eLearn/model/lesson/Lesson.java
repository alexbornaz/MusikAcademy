package com.grandProject.eLearn.model.lesson;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.grandProject.eLearn.model.course.Course;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="lessons")
public class Lesson {
    private String title;

    private String url;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="course_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Course course;
    public Lesson() {

    }

    public Lesson(String title, String url, Course course) {
        this.title = title;
        this.url = url;
        this.course = course;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
