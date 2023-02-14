package com.grandProject.eLearn.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.grandProject.eLearn.model.course.Course;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="users",uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "emailAddress")
})
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    @JsonIgnore
    private String password;
    private String emailAddress;
    private String firstName;
    private String lastName;
    private Set<String> roles =new HashSet<>();
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_enrolled_courses",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "enrolled_courses_id")
    )
    private Set<Course> enrolledCourses = new HashSet<>();

    public User(String username, String password, String emailAddress, String firstName, String lastName, Set<String> roles) {
        this.username = username;
        this.password = password;
        this.emailAddress = emailAddress;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roles = roles;
    }

    public User() {

    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void setEnrolledCourses(Set<Course> enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> role) {
        this.roles = role;
    }

    public void addCourse(Course course){
        this.enrolledCourses.add(course);
    }

    public void addRole(String role){
        this.roles.add(role);
    }
}
