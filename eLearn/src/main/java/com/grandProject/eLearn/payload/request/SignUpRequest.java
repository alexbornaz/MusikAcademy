package com.grandProject.eLearn.payload.request;

public class SignUpRequest {

        private String username;
        private String firstName;
        private String lastName;



        private String emailAddress;

        private String role;


        private String password;

    public SignUpRequest(String username, String firstName, String lastName, String emailAddress, String role, String password) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.role = role;
        this.password = password;
    }

    public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getEmailAddress() {
            return emailAddress;
        }

        public void setEmailAddress(String email) {
            this.emailAddress = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getRole() {
            return this.role;
        }

        public void setRole(String role) {
            this.role = role;
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
}
