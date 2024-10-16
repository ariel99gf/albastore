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

    /*@Test TODO
    void testCreateUser() {
        User user = new User();
        user.username = "newuser";
        user.password = "newpass";

        userService.createUser(user);

        verify(userRepository, times(1)).persist(user);
    }*/

    @Test
    void testFindUserById() {
        User user = new User();
        user.id = 1L;
        when(userRepository.findById(1L)).thenReturn(user);

        User result = userService.findUserById(1L);

        assertNotNull(result);
        assertEquals(1L, result.id);
    }

    @Test
    void testUpdateUser() {
        User existingUser = new User();
        existingUser.id = 1L;
        existingUser.username = "existinguser";
        existingUser.password = "existingpass";

        User updatedUser = new User();
        updatedUser.username = "updateduser";
        updatedUser.password = "updatedpass";

        when(userRepository.findById(1L)).thenReturn(existingUser);

        userService.updateUser(1L, updatedUser);

        assertEquals("updateduser", existingUser.username);
        assertEquals("updatedpass", existingUser.password);
    }

    @Test
    void testDeleteUser() {
        userService.deleteUser(1L);

        verify(userRepository, times(1)).deleteById(1L);
    }
}