package com.grandProject.eLearn.dto.response;

public class CreatedCourseMessage {
    private String message;
    private Long id;

    public CreatedCourseMessage(String message,Long id) {
        this.message = message;
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
