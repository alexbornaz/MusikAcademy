package com.grandProject.eLearn.dto.request;

public class ChargeRequest {
    private String token;
    private Long courseId;

    public ChargeRequest(String token, Long courseId) {
        this.token = token;
        this.courseId = courseId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
}
