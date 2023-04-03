package com.ck.ai.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author
 * 登录成功的处理器
 */
@Configuration
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse
            response, Authentication authentication) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter pw = response.getWriter();
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
// 将用户信息放进去
        map.put("msg", authentication.getPrincipal());
        map.put("success", true);
        pw.write(new ObjectMapper().writeValueAsString(map));
        pw.flush();
        pw.close();
    }
}