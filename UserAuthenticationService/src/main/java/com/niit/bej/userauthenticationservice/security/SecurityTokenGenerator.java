package com.niit.bej.userauthenticationservice.security;

import com.niit.bej.userauthenticationservice.domain.User;

import java.util.Map;

public interface SecurityTokenGenerator {

    Map<String, String> generateToken(User user);
}
