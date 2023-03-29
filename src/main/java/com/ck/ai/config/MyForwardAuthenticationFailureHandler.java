package com.ck.ai.config;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyForwardAuthenticationFailureHandler implements AuthenticationFailureHandler {
    private String url;
    public MyForwardAuthenticationFailureHandler(String url) {
        this.url = url;
    }
    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response, AuthenticationException exception) throws
            IOException, ServletException {
        response.sendRedirect(url);
    }
}
