package com.ck.ai.service;

import com.ck.ai.bean.entity.User;

public interface UserService {
    User registerUser(User user);
    User loginUser(String usernameOrEmail, String password);
}

