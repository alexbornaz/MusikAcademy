package com.grandProject.eLearn.model.course;


import com.grandProject.eLearn.model.lesson.Lesson;
import com.grandProject.eLearn.model.user.User;
import jakarta.persistence.*;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;



@Entity
public class Course {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    private String description;

    private List<String> requirements = new ArrayList<>();

    private List<String> outline = new ArrayList<>();
    @ManyToOne(optional = false)
    private User creator;
    private BigDecimal defaultPrice=BigDecimal.ZERO;

    public Course(String title, String description, List<String> requirements, List<String> outline, User creator, BigDecimal defaultPrice) {
        this.title = title;
        this.description = description;
        this.requirements = requirements;
        this.outline = outline;
        this.creator = creator;
        this.defaultPrice = defaultPrice;
    }

    public Course() {

    }


    public List<String> getRequirements() {
        return requirements;
    }

    public void setRequirements(List<String> requirements) {
        this.requirements = requirements;
    }

    public List<String> getOutline() {
        return outline;
    }

    public void setOutline(List<String> outline) {
        this.outline = outline;
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


    public User getCreator() {
        return creator;
    }


    public BigDecimal getDefaultPrice() {
        return defaultPrice;
    }

    public void setDefaultPrice(BigDecimal defaultPrice) {
        this.defaultPrice = defaultPrice;
    }
}
