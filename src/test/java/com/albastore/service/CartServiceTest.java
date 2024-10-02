package com.albastore.service;

import com.albastore.domain.Cart;
import com.albastore.repository.CartRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CartServiceTest {

    @Mock
    CartRepository cartRepository;

    @InjectMocks
    CartService cartService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListAllCarts() {
        Cart cart = new Cart();
        cart.userId = "user1";
        cart.totalPrice = 100.0;
        when(cartRepository.listAll()).thenReturn(Collections.singletonList(cart));

        List<Cart> result = cartService.listAllCarts();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("user1", result.get(0).userId);
    }

    @Test
    void testFindCartById() {
        Cart cart = new Cart();
        cart.id = 1L;
        when(cartRepository.findById(1L)).thenReturn(cart);

        Cart result = cartService.findCartById(1L);

        assertNotNull(result);
        assertEquals(1L, result.id);
    }

    @Test
    void testCreateCart() {
        Cart cart = new Cart();
        cart.userId = "user1";
        cart.totalPrice = 100.0;

        cartService.createCart(cart);

        verify(cartRepository, times(1)).persist(cart);
    }

    @Test
    void testUpdateCart() {
        Cart existingCart = new Cart();
        existingCart.id = 1L;
        existingCart.userId = "user1";
        existingCart.totalPrice = 100.0;

        Cart updatedCart = new Cart();
        updatedCart.userId = "user2";
        updatedCart.totalPrice = 200.0;

        when(cartRepository.findById(1L)).thenReturn(existingCart);

        cartService.updateCart(1L, updatedCart);

        assertEquals("user2", existingCart.userId);
        assertEquals(200.0, existingCart.totalPrice);
    }

    @Test
    void testDeleteCart() {
        cartService.deleteCart(1L);

        verify(cartRepository, times(1)).deleteById(1L);
    }
}