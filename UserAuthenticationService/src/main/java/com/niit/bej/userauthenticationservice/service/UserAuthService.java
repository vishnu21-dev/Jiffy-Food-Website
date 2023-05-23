package com.niit.bej.userauthenticationservice.service;

import com.niit.bej.userauthenticationservice.domain.User;
import com.niit.bej.userauthenticationservice.exception.UserAlreadyExistsException;
import com.niit.bej.userauthenticationservice.exception.UserNotFoundException;

public interface UserAuthService {

    User register(User user) throws UserAlreadyExistsException;

    User login(User user) throws UserNotFoundException;
}
