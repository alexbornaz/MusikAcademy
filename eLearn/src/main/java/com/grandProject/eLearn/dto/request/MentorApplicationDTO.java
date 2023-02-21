package com.grandProject.eLearn.dto.request;

public class MentorApplicationDTO {
    private String username;
    private String application;

    public MentorApplicationDTO(String username, String application) {
        this.username = username;
        this.application = application;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }
}
