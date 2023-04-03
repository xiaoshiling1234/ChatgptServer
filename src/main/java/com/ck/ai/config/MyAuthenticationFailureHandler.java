package com.ck.ai.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
/**
 * @Author 武汉尚学堂
 * 登录失败的处理器
 */
@Configuration
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse
            response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter pw = response.getWriter();
        Map<String, Object> map = new HashMap<>();
        map.put("code", 401);
        map.put("success", false);
        if (exception instanceof LockedException) {
            map.put("msg", "账户被锁定，登陆失败！");
        } else if (exception instanceof BadCredentialsException) {
            map.put("msg", "账户或者密码错误，登陆失败！");
        } else if (exception instanceof DisabledException) {
            map.put("msg", "账户被禁用，登陆失败！");
        } else if (exception instanceof AccountExpiredException) {
            map.put("msg", "账户已过期，登陆失败！");
        } else if (exception instanceof CredentialsExpiredException) {
            map.put("msg", "密码已过期，登陆失败！");
        } else {
            map.put("msg", "登陆失败！");
        }
        System.out.println(exception.getClass().getSimpleName());
        pw.write(new ObjectMapper().writeValueAsString(map));
        pw.flush();
        pw.close();
    }
}