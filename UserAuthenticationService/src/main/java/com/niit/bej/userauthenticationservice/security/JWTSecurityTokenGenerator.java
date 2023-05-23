package com.niit.bej.userauthenticationservice.security;

import com.niit.bej.userauthenticationservice.domain.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class JWTSecurityTokenGenerator implements SecurityTokenGenerator {
    @Override
    public Map<String, String> generateToken(User user) {
        String generatedToken = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, "secretKey")
                .setSubject(user.getEmailId())
                .setIssuedAt(new Date())
                .compact();
        return Map.of(
                "token", generatedToken,
                "message", "Token Generated and " + user.getEmailId() + " Logged in Successfully!"
        );
    }
}
