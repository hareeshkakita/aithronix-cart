package com.aithronix.aithronix_cart.service;

import com.aithronix.aithronix_order.dto.OrderResponse;

import java.util.UUID;

public interface CheckoutService {
    OrderResponse processCheckout(UUID userId);
}
