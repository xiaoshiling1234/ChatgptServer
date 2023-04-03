package com.ck.ai.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Component
public class RestAuthorizationAccessDeniedHandler implements org.springframework.security.web.access.AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException,
            ServletException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setHeader("Content-Type", "application/json;charset=utf-8");
        Map<String, Object> data = new HashMap<>(4);
        data.put("code", "403");
        data.put("msg", "今日次数已用完，请等待明日或开通会员!");
        data.put("success", false);
        // 拿到响应流
        PrintWriter writer = response.getWriter();
        // 创建类型转换器
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(data);
        // 将数据写出去
        writer.write(s);
        writer.flush();
        writer.close();
    }
}