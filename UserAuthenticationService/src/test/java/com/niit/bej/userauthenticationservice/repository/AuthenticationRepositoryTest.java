package com.niit.bej.userauthenticationservice.repository;


import com.niit.bej.userauthenticationservice.domain.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

@ExtendWith(SpringExtension.class)
public class AuthenticationRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User user;


    @BeforeEach
    public void setUp() {
        user = new User("saisai123@gmail.com", "kiran123");
    }

    @AfterEach
    public void tearDown() {
        user = null;
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("Test for saving user/merchant database")
    public void saveUserOrMerchant() {
        userRepository.save(user);
        User user1 = userRepository.findById(user.getEmailId()).get();
        assertEquals(user.getEmailId(), user1.getEmailId());
        assertNotNull(user1);

    }

}
