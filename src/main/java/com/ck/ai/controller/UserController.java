package com.ck.ai.controller;

import com.ck.ai.bean.entity.User;
import com.ck.ai.dao.mapper.UserMapper;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@Api(tags = "user")
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @PostMapping("/register")
    public void register(@RequestBody User user) {
        userMapper.insert(user);
    }

    @PostMapping("/login")
    public boolean login(@RequestBody User user) {
        User dbUser = userMapper.selectByUsername(user.getUsername());
        return dbUser != null && dbUser.getPassword().equals(user.getPassword());
    }
}
