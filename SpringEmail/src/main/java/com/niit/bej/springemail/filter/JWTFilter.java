package com.niit.bej.springemail.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

public class JWTFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        final String authHeader = request.getHeader("Authorization");
        if (request.getMethod().equals("OPTIONS")) {

            response.setStatus(HttpServletResponse.SC_OK);
            filterChain.doFilter(request, response);
        } else if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new ServletException("Missing or Invalid Token");
        }

        String token = authHeader.substring(7);

        Claims claims = Jwts.parser().setSigningKey("secretKey").parseClaimsJws(token).getBody();

        request.setAttribute("claims", claims);

        filterChain.doFilter(request, response);
    }
}
