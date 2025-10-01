package com.aithronix.aithronix_cart.service.impl;

import com.aithronix.aithronix_cart.client.OrderClient;
import com.aithronix.aithronix_cart.mapper.CartMapper;
import com.aithronix.aithronix_cart.model.Cart;
import com.aithronix.aithronix_cart.service.CartService;
import com.aithronix.aithronix_cart.service.CheckoutService;
import com.aithronix.aithronix_order.dto.OrderRequest;
import com.aithronix.aithronix_order.dto.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CheckoutServiceImpl implements CheckoutService {

    private final CartService cartService;
    private final OrderClient orderClient;
    private final CartMapper cartMapper;

    @Override
    public OrderResponse processCheckout(UUID userId) {
        Cart cart = cartService.getCart(userId);
        if (cart == null || cart.getItems().isEmpty()) {
            throw new IllegalStateException("Cannot checkout: Cart is empty or does not exist");
        }

        OrderRequest orderRequest = new OrderRequest()
                .userId(userId)
                .items(cart.getItems().stream()
                        .map(cartMapper::cartItemToOrderItemRequest)
                        .toList());

        try {
            OrderResponse response = orderClient.createOrder(orderRequest);
            cartService.clearCart(userId);
            return response;
        } catch (Exception e) {
            throw new RuntimeException("Failed to process checkout: " + e.getMessage(), e);
        }
    }
}
