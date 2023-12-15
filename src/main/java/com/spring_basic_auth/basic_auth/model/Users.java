package com.spring_basic_auth.basic_auth.model;


import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userid;

    @Column(nullable = false)
    private String userName;
    @Column(nullable = false)
    private String emailAddress;
    @Column(nullable = false)
    private Gender gender;

    public enum Gender {
        MALE, FEMALE
    }

    public Users() {
    }

    public Users(String userName, String emailAddress, String role) {
        this.userName = userName;
        this.emailAddress = emailAddress;

    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return userid == users.userid && Objects.equals(userName, users.userName) && Objects.equals(emailAddress, users.emailAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userid, userName, emailAddress);
    }

    @Override
    public String toString() {
        return "Users{" +
                "userid=" + userid +
                ", userName='" + userName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                '}';
    }
}
