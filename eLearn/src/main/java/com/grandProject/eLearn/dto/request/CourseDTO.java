package com.grandProject.eLearn.dto.request;

public class CourseDTO {
    private String title;
    private String description;
    private String requirements;
    private String outline;
    private String price;
    private String image;
    private String creatorUsername;


    public CourseDTO(String title, String description, String requirements, String outline, String price, String image, String creatorUsername) {
        this.title = title;
        this.description = description;
        this.requirements = requirements;
        this.outline = outline;
        this.price = price;
        this.image = image;
        this.creatorUsername = creatorUsername;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCreatorUsername() {
        return creatorUsername;
    }

    public void setCreatorUsername(String creatorUsername) {
        this.creatorUsername = creatorUsername;
    }


}
