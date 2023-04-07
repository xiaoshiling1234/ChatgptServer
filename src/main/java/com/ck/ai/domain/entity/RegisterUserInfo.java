package com.ck.ai.domain.entity;

import lombok.Data;

@Data
public class RegisterUserInfo {
    private String username;
    private String password;
    private String verificationCode;
}
