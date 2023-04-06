package com.ck.ai.controller;

import com.ck.ai.domain.JsonResult;
import com.ck.ai.domain.entity.User;
import com.ck.ai.service.UserService;
import com.ck.ai.util.JWTUtil;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 测试一下
 * </p>
 *
 * @author duguotao
 * @version 1.0.0
 * @since Created in 2021/11/11
 */
@RestController
@RequiredArgsConstructor
public class UserController {

    final UserService userService;
    final HttpServletRequest httpServletRequest;

    @GetMapping("/login")
    public JsonResult<String> login(String username, String password) {
        userService.login(username, password);
        return JsonResult.OK(JWTUtil.createToken(username));
    }

    @PostMapping("/register")
    public JsonResult registerUser(@RequestBody User user) {
        User existingUser = userService.getUserByUsername(user.getUsername());
        if (existingUser != null) {
            return JsonResult.Fail(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Username already exists");
        }
        int rowsInserted = userService.addUser(user);
        if (rowsInserted > 0) {
            return JsonResult.OK("User registered successfully");
        }
        return JsonResult.Fail(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Failed to register user");
    }

    @PostMapping("/update")
    public JsonResult updateUser(
            @ApiParam(name = "token", value = "token", required = true)
            @RequestHeader(name = "token") String token,
            @RequestBody User user) {
        String principal = (String)SecurityUtils.getSubject().getPrincipal();
        String username = JWTUtil.getUsername(principal);
        user.setUsername(username);
        int rowsUpdated = userService.updateUserByUsername(user);
        if (rowsUpdated > 0) {
            return JsonResult.OK("User updated successfully");
        }
        return JsonResult.Fail(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Failed to update user");
    }
}