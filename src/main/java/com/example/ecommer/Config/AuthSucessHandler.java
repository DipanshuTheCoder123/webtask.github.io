package com.example.ecommer.Config;

import java.io.IOException;
import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Service
public class AuthSucessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        // Get authorities and roles
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Set<String> role = AuthorityUtils.authorityListToSet(authorities);

        // Redirect based on role
        if (role.contains("ROLE_ADMIN")) { // Fixed typo here
            response.sendRedirect("admin/index");
        } else {
            response.sendRedirect("/");
        }
    }
}
