package com.niit.bej.userauthenticationservice.service;

import com.niit.bej.userauthenticationservice.domain.User;
import com.niit.bej.userauthenticationservice.exception.UserAlreadyExistsException;
import com.niit.bej.userauthenticationservice.exception.UserNotFoundException;
import com.niit.bej.userauthenticationservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAuthServiceImpl implements UserAuthService {

    private final UserRepository userRepository;

    @Autowired
    public UserAuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User register(User user) throws UserAlreadyExistsException {
        Optional<User> newUser = userRepository.findById(user.getEmailId());
        if (newUser.isPresent()) {
            throw new UserAlreadyExistsException("User already exists");
        }
        return userRepository.save(user);
    }

    @Override
    public User login(User user) throws UserNotFoundException {
        if (userRepository.findById(user.getEmailId()).isPresent()) {
            User loggedInUser = userRepository.findById(user.getEmailId()).get();
            if (loggedInUser.getEmailId().equals(user.getEmailId()) && loggedInUser.getPassword().equals(user.getPassword()))
                return loggedInUser;
            else throw new UserNotFoundException("User not found in database!");
        } else {
            throw new UserNotFoundException("User does not exists!");
        }
    }
}
