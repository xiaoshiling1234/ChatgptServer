package com.ck.ai.service;

import com.ck.ai.domain.entity.User;

public interface UserService {
    User registerUser(User user);
    User loginUser(String usernameOrEmail, String password);
}

