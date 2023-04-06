package com.ck.ai.domain.entity;

import com.ck.ai.domain.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String nickname;
    private Integer freeChatTimes;
    private RoleEnum role;
    private Integer ban;
    private Date expireDate;
    private Date createdAt;
    private Date updatedAt;
}
