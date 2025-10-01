package com.aithronix.aithronix_cart.service.impl;

import com.aithronix.aithronix_cart.model.Cart;
import com.aithronix.aithronix_cart.model.CartItem;
import com.aithronix.aithronix_cart.repository.CartRepository;
import com.aithronix.aithronix_cart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    @Override
    public Cart getCart(UUID userId) {
        return Optional.ofNullable(cartRepository.findByUserId(userId))
                .orElseGet(() -> new Cart(userId, new ArrayList<>(), Instant.now()));
    }

    @Override
    public Cart addItem(UUID userId, UUID productId, int quantity) {
       // ProductDto product = productClient.getProduct(productId); // validate product & price

        Cart cart = getCart(userId);

        cart.getItems().stream()
                .filter(i -> i.getProductId().equals(productId))
                .findFirst()
                .ifPresentOrElse(
                        i -> i.setQuantity(i.getQuantity() + quantity),
                        () -> cart.getItems().add(new CartItem(productId, quantity, 100))
                );

        cart.setUpdatedAt(Instant.now());
        cartRepository.save(cart);
        return cart;
    }

    @Override
    public Cart removeItem(UUID userId, UUID productId) {
        Cart cart = getCart(userId);
        cart.getItems().removeIf(i -> i.getProductId().equals(productId));
        cart.setUpdatedAt(Instant.now());
        cartRepository.save(cart);
        return cart;
    }

    @Override
    public void clearCart(UUID userId) {
        cartRepository.delete(userId);
    }

    @Override
    public Cart updateItem(UUID userId, UUID productId, int quantity) {
        Cart cart = getCart(userId);
        cart.getItems().forEach(item -> {
            if (item.getProductId().equals(productId)) {
                item.setQuantity(quantity);
            }
        });
        cart.setUpdatedAt(Instant.now());
        cartRepository.save(cart);
        return cart;
    }
}
