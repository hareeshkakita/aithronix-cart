package com.aithronix.aithronix_cart.service;

import com.aithronix.aithronix_cart.model.Cart;

public interface CartService {

    public Cart getCart(Long userId) ;
    public Cart addItem(Long userId, Long productId, int quantity);
    public Cart removeItem(Long userId, Long productId);
    public void clearCart(Long userId);
    public Cart updateItem(Long userId, Long productId, int quantity);

}
