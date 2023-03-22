package com.ck.ai.controller;

import com.ck.ai.dao.entity.User;
import com.ck.ai.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        try {
            userService.registerUser(user);
            return ResponseEntity.ok("User registered successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/user/{username}")
    public User getUser(@PathVariable("username") String username) {
        List<com.ck.ai.dao.entity.User> userList = userService.findByUsername(username);
        if (userList.size()>0){
            return userList.get(0);
        }
        return null;
    }
}
