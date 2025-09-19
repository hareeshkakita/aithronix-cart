package com.aithronix.aithronix_cart.controller;

import com.aithronix.aithronix_cart.model.Cart;
import com.aithronix.aithronix_cart.service.CartService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart")
@Tag(name = "Cart", description = "Cart management APIs")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping("/{userId}")
    public Cart getCart(@PathVariable Long userId) {
        return cartService.getCart(userId);
    }

    @PostMapping("/{userId}/items")
    public Cart addItem(@PathVariable Long userId,
                        @RequestParam Long productId,
                        @RequestParam int quantity) {
        return cartService.addItem(userId, productId, quantity);
    }

    @PatchMapping("/{userId}/items/{productId}")
    public Cart updateItem(@PathVariable Long userId,
                           @PathVariable Long productId,
                           @RequestParam int quantity) {
        return cartService.updateItem(userId, productId, quantity);
    }

    @DeleteMapping("/{userId}/items/{productId}")
    public Cart removeItem(@PathVariable Long userId,
                           @PathVariable Long productId) {
        return cartService.removeItem(userId, productId);
    }

    @DeleteMapping("/{userId}")
    public void clearCart(@PathVariable Long userId) {
        cartService.clearCart(userId);
    }
}
