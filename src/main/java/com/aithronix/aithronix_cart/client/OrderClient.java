package com.aithronix.aithronix_cart.client;

import com.aithronix.aithronix_order.dto.OrderRequest;
import com.aithronix.aithronix_order.dto.OrderResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "order-service", url = "${services.order.url}")
public interface OrderClient {
    @PostMapping("/api/v1/orders")
    OrderResponse createOrder(@RequestBody OrderRequest request);
}
