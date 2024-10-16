package com.albastore.resource;

import com.albastore.domain.Cart;
import com.albastore.service.CartService;
import jakarta.ws.rs.core.SecurityContext;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/*@ExtendWith(MockitoExtension.class)
TODO public class CartResourceTest {

    @Mock
    CartService cartService;

    @Mock
    SecurityContext securityContext;

    @InjectMocks
    CartResource cartResource;

    @BeforeEach
    void setUp() {
        cartResource.securityContext = securityContext;
    }

    @Test
    void testGetAllCartsAsAdmin() {
        when(securityContext.isUserInRole("admin")).thenReturn(true);
        List<Cart> carts = List.of(new Cart());
        when(cartService.listAllCarts()).thenReturn(carts);

        List<Cart> result = cartResource.getAllCarts();

        assertEquals(carts, result);
        verify(cartService, times(1)).listAllCarts();
    }

    @Test
    void testGetCartByIdAsUser() {
        when(securityContext.isUserInRole("user")).thenReturn(true);
        Cart cart = new Cart();
        when(cartService.findCartById(1L)).thenReturn(cart);

        Cart result = cartResource.getCartById(1L);

        assertEquals(cart, result);
        verify(cartService, times(1)).findCartById(1L);
    }

    @Test
    void testCreateCartAsAdmin() {
        when(securityContext.isUserInRole("admin")).thenReturn(true);
        Cart cart = new Cart();

        Response response = cartResource.createCart(cart);

        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
        verify(cartService, times(1)).createCart(cart);
    }

    @Test
    void testUpdateCartAsAdmin() {
        when(securityContext.isUserInRole("admin")).thenReturn(true);
        Cart cart = new Cart();

        Response response = cartResource.updateCart(1L, cart);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        verify(cartService, times(1)).updateCart(1L, cart);
    }

    @Test
    void testDeleteCartAsAdmin() {
        when(securityContext.isUserInRole("admin")).thenReturn(true);

        Response response = cartResource.deleteCart(1L);

        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), response.getStatus());
        verify(cartService, times(1)).deleteCart(1L);
    }
}*/