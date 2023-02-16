package com.grandProject.eLearn.model;

import jakarta.persistence.*;

@Entity
@Table(name = "subscribers",uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
public class Subscriber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String email;
    private boolean subscribed=true;

    public Subscriber(String email) {
        this.email = email;
        this.subscribed=true;
    }

    public Subscriber() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isSubscribed() {
        return subscribed;
    }

    public void subscribe(){
        this.subscribed = true;
    }
    public void unsubscribe(){
        this.subscribed=false;
    }
}
