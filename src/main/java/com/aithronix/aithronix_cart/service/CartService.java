package com.aithronix.aithronix_cart.service;

import com.aithronix.aithronix_cart.model.Cart;

import java.util.UUID;

public interface CartService {

    public Cart getCart(UUID userId) ;
    public Cart addItem(UUID userId, UUID productId, int quantity);
    public Cart removeItem(UUID userId, UUID productId);
    public void clearCart(UUID userId);
    public Cart updateItem(UUID userId, UUID productId, int quantity);

}
