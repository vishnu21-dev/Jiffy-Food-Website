package com.niit.bej.userauthenticationservice.Service;


import com.niit.bej.userauthenticationservice.domain.User;
import com.niit.bej.userauthenticationservice.exception.UserAlreadyExistsException;
import com.niit.bej.userauthenticationservice.exception.UserNotFoundException;
import com.niit.bej.userauthenticationservice.repository.UserRepository;
import com.niit.bej.userauthenticationservice.service.UserAuthServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static junit.framework.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class AuthenticationServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    private UserAuthServiceImpl userAuthService;

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User("gurijala12@gmail.com", "sai@123");
    }

    @AfterEach
    public void tearDown() {
        user = null;
    }

    @Test
    public void testForSaveUserSuccess() throws UserAlreadyExistsException {
        when(userRepository.findById(user.getEmailId())).thenReturn(Optional.ofNullable(null));
        when(userRepository.save(user)).thenReturn(user);
        assertEquals(user, userAuthService.register(user));
    }

    @Test
    public void testForSaveUserFailure() throws UserAlreadyExistsException {
        when(userRepository.findById(user.getEmailId())).thenReturn(Optional.ofNullable(user));
        assertThrows(UserAlreadyExistsException.class, () -> userAuthService.register(user));

    }


    @Test
    public void testloginSuccess() throws UserNotFoundException {
//        User user = new User();
//        user.setEmailId("abc");
//        user.setPassword("abcd");
        when(userRepository.findById(Mockito.any())).thenReturn(Optional.of(user));
        User userRecieved = userAuthService.login(user);
        assertEquals(user.getEmailId(), userRecieved.getEmailId());

    }

    @Test
    public void testloginfailed() throws UserNotFoundException {
        User user1 = new User();
        user1.setEmailId("kirann@gmail.com");
        user1.setPassword("abcd");
        when(userRepository.findById(Mockito.any())).thenReturn(Optional.of(user));
        assertThrows(UserNotFoundException.class, () -> userAuthService.login(user1));

//        User userRecieved = userAuthService.login(user);
//        assertEquals(user1.getEmailId(),userRecieved.getEmailId());

    }

}
