package com.aithronix.aithronix_cart.controller;

import com.aithronix.aithronix_cart.model.Cart;
import com.aithronix.aithronix_cart.service.CartService;
import com.aithronix.aithronix_cart.service.CheckoutService;
import com.aithronix.aithronix_order.dto.OrderResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/cart")
@Tag(name = "Cart", description = "Cart management APIs")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final CheckoutService checkoutService;

    @GetMapping("/{userId}")
    public Cart getCart(@PathVariable UUID userId) {
        return cartService.getCart(userId);
    }

    @PostMapping("/{userId}/checkout")
    public ResponseEntity<OrderResponse> checkout(@PathVariable UUID userId) {
        return ResponseEntity.ok(checkoutService.processCheckout(userId));
    }

    @PostMapping("/{userId}/items")
    public Cart addItem(@PathVariable UUID userId,
                        @RequestParam UUID productId,
                        @RequestParam int quantity) {
        return cartService.addItem(userId, productId, quantity);
    }

    @PatchMapping("/{userId}/items/{productId}")
    public Cart updateItem(@PathVariable UUID userId,
                           @PathVariable UUID productId,
                           @RequestParam int quantity) {
        return cartService.updateItem(userId, productId, quantity);
    }

    @DeleteMapping("/{userId}/items/{productId}")
    public Cart removeItem(@PathVariable UUID userId,
                           @PathVariable UUID productId) {
        return cartService.removeItem(userId, productId);
    }

    @DeleteMapping("/{userId}")
    public void clearCart(@PathVariable UUID userId) {
        cartService.clearCart(userId);
    }
}
