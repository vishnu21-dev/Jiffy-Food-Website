package com.niit.bej.userauthenticationservice.controller;

import com.niit.bej.userauthenticationservice.domain.User;
import com.niit.bej.userauthenticationservice.exception.UserAlreadyExistsException;
import com.niit.bej.userauthenticationservice.exception.UserNotFoundException;
import com.niit.bej.userauthenticationservice.security.SecurityTokenGenerator;
import com.niit.bej.userauthenticationservice.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/userAuth")
public class UserAuthController {

    private final UserAuthService userAuthService;

    private final SecurityTokenGenerator securityTokenGenerator;


    @Autowired
    public UserAuthController(UserAuthService userAuthService, SecurityTokenGenerator securityTokenGenerator) {
        this.userAuthService = userAuthService;
        this.securityTokenGenerator = securityTokenGenerator;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        try {
            User registerUser = userAuthService.register(user);
            return new ResponseEntity<>(registerUser, HttpStatus.CREATED);
        } catch (UserAlreadyExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        try {
            User loggedInUser = userAuthService.login(user);
            Map<String, String> tokenMap = securityTokenGenerator.generateToken(loggedInUser);
            return new ResponseEntity<>(tokenMap, HttpStatus.ACCEPTED);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
}
