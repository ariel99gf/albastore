package com.albastore.service;

import com.albastore.domain.Cart;
import com.albastore.repository.CartRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class CartService {

    @Inject
    CartRepository cartRepository;

    public List<Cart> listAllCarts() {
        return cartRepository.listAll();
    }

    public Cart findCartById(Long id) {
        return cartRepository.findById(id);
    }

    @Transactional
    public void createCart(Cart cart) {
        cartRepository.persist(cart);
    }

    @Transactional
    public void updateCart(Long id, Cart cart) {
        Cart entity = cartRepository.findById(id);
        if (entity != null) {
            entity.userId = cart.userId;
            entity.totalPrice = cart.totalPrice;
            entity.products = cart.products;
        }
    }

    @Transactional
    public void deleteCart(Long id) {
        cartRepository.deleteById(id);
    }
}