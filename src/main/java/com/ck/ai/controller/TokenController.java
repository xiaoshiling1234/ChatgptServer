package com.ck.ai.controller;

import com.ck.ai.service.TokenService;
import com.ck.ai.bean.User;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@Api(tags = "token")
@RestController
public class TokenController {
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public String generateToken(@RequestBody User user) {
        // Authenticate user
        // ...

        // Generate token
        String token = tokenService.generateToken(user);

        // Return token
        return token;
    }

    @GetMapping("/validateToken")
    public boolean validateToken(@RequestParam("token") String token) {
        return tokenService.validateToken(token);
    }
}
