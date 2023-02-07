package com.grandProject.eLearn.model.course;


import com.grandProject.eLearn.model.user.User;
import jakarta.persistence.*;


import java.math.BigDecimal;
import java.util.Optional;


@Entity
public class Course {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    private String description;

    private String requirements;

    private String outline;
    private String image;
    @ManyToOne(optional = false)
    private User creator;
    private BigDecimal defaultPrice=BigDecimal.ZERO;

    public Course(String title, String description, String requirements, String outline, User creator, BigDecimal defaultPrice,String image) {
        this.title = title;
        this.description = description;
        this.requirements = requirements;
        this.outline = outline;
        this.creator = creator;
        this.defaultPrice = defaultPrice;
        this.image = image;
    }

    public Course() {

    }


    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public String getOutline() {
        return outline;
    }

    public void setOutline(String outline) {
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

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public BigDecimal getDefaultPrice() {
        return defaultPrice;
    }

    public void setDefaultPrice(BigDecimal defaultPrice) {
        this.defaultPrice = defaultPrice;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
