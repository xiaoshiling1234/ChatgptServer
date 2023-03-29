//package com.ck.ai.controller;
//
//import com.ck.ai.bean.ResultResponse;
//import com.ck.ai.bean.entity.User;
//import com.ck.ai.dao.mapper.UserMapper;
//import com.ck.ai.service.TokenService;
//import com.ck.ai.service.UserService;
//import io.swagger.annotations.Api;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//
//@Api(tags = "user")
//@RestController
//@RequestMapping("/users")
//public class UserController {
//    @Autowired
//    private UserService userService;
//
//    @PostMapping("/register")
//    public ResponseEntity<?> registerUser(@Valid @RequestBody User user) {
//        try {
//            User registeredUser = userService.registerUser(user);
//            return ResponseEntity.ok(registeredUser);
//        } catch (RuntimeException e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }
//
//    @Autowired
//    private TokenService tokenService;
//
//    @PostMapping("/login")
//    public ResponseEntity<?> loginUser(@RequestParam("username_or_email") String usernameOrEmail, @RequestParam("password") String password) {
//        try {
//            User user = userService.loginUser(usernameOrEmail, password);
//            user.setToken(tokenService.generateToken(user));
//            return ResponseEntity.ok(user);
//        } catch (RuntimeException e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }
//
//
//    @GetMapping("/validateToken")
//    public boolean validateToken(@RequestParam("token") String token) {
//        return tokenService.validateToken(token);
//    }
//}
