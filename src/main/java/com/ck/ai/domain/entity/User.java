package com.ck.ai.domain.entity;

import com.ck.ai.domain.enums.Gender;
import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

@Data
public class User {
    private Long userId;
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String avatar;
    private String phoneNumber;
    private Gender gender;
    private Date dateOfBirth;
    private String location;
    private String bio;
    private boolean isActive = true;
    private boolean isAdmin = false;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    private String token;
    // getter和setter方法省略
}
