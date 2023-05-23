package com.niit.bej.userauthenticationservice.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class User {

    @Id
    private String emailId;
    private String password;

    public User(String emailId, String password) {
        this.emailId = emailId;
        this.password = password;
    }

    public User() {
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(emailId, user.emailId) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emailId, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "emailId='" + emailId + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


}
