package com.niit.bej.orderservice.filter;

import io.jsonwebtoken.Jwts;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

public class JWTFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        ServletOutputStream outputStream = httpServletResponse.getOutputStream();

        String authorizationHeader = httpServletRequest.getHeader("Authorization");

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            outputStream.println("Authorization Header is Missing!");
            outputStream.close();
        } else {
            String jwtToken = authorizationHeader.substring("Bearer ".length());
            String emailId = Jwts.parser().setSigningKey("secretKey").parseClaimsJws(jwtToken).getBody().getSubject();
            httpServletRequest.setAttribute("emailId", emailId);
        }
        chain.doFilter(request, response);
    }
}
