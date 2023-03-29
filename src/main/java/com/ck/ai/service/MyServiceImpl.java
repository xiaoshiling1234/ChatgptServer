package com.ck.ai.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

public class MyServiceImpl implements MyService {
    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication
            authentication) {
        Object obj = authentication.getPrincipal();
        if (obj instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) obj;
            Collection<? extends GrantedAuthority> authorities =
                    userDetails.getAuthorities();
            return authorities.contains(new
                    SimpleGrantedAuthority(request.getRequestURI()));
        }
        return false;
    }
}

