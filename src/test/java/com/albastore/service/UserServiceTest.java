package com.albastore.service;

import com.albastore.domain.User;
import com.albastore.repository.UserRepository;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    PanacheQuery<User> query;

    @InjectMocks
    UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAuthenticateSuccess() {
        User user = new User();
        user.username = "testuser";
        user.password = "testpass";

        when(userRepository.find("username", "testuser")).thenReturn(query);
        when(query.firstResultOptional()).thenReturn(Optional.of(user));

        User result = userService.authenticate("testuser", "testpass");

        assertNotNull(result);
        assertEquals("testuser", result.username);
    }

    @Test
    void testAuthenticateFailure() {
        when(userRepository.find("username", "wronguser")).thenReturn(query);
        when(query.firstResultOptional()).thenReturn(Optional.empty());

        User result = userService.authenticate("wronguser", "wrongpass");

        assertNull(result);
    }
}