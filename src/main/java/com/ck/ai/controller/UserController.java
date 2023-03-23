package com.ck.ai.controller;

import com.ck.ai.bean.ResultResponse;
import com.ck.ai.bean.entity.User;
import com.ck.ai.dao.mapper.UserMapper;
import com.ck.ai.service.TokenService;
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

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public String generateToken(@RequestBody User user) {
        // Authenticate user
        User dbUser = userMapper.selectByUsername(user.getUsername());
        if(dbUser != null && dbUser.getPassword().equals(user.getPassword())){
            // Generate token
            String token = tokenService.generateToken(user);

            // Return token
            return token;
        }
        return null;
    }

    @GetMapping("/validateToken")
    public boolean validateToken(@RequestParam("token") String token) {
        return tokenService.validateToken(token);
    }
}
